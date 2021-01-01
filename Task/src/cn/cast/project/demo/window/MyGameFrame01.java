package cn.cast.project.demo.window;
import cn.cast.project.demo.begin.*;
import cn.cast.project.demo.util.ImageUtil;
import cn.cast.project.demo.util.VoiceUtil;
import img.MyGameFrame;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyGameFrame01 extends Frame {
   // int x = 100;
    //窗口初始化

    public void init() {
        setTitle("坦克大战");
        setSize(Constant.windowWidth, Constant.windowHeight);//大小
        setLocation(Constant.windowX, Constant.windowY);//位置
        setVisible(true);//窗口是否可见
        setResizable(true);//窗口是否可调整
        new PaintThread().start();
        new CreateOtherTank().start();
        //监听窗口事件并关闭java程序--匿名类
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                //监听到了就停止
                System.exit(0);
            }
        });
        //监听键盘事件
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                myTank.keypress(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                myTank.keyrest(e);
            }
        });



//        //1s重画40次窗口

//        //敌方坦克

    }
   //加载图片
    BufferedImage tankImage = ImageUtil.getImage("img/mytank.gif");
    MyTank myTank=new MyTank(tankImage,Constant.mytankX,Constant.mytankY,Constant.mytankWidth,Constant.mytankHeight,Constant.mytankspeed);
    //杀敌数
    public int killCount=0;
    //画图
    @Override
    public void paint(Graphics g) {
        //绘制黑色背景
        g.fillRect(0, 0, Constant.windowWidth, Constant.windowHeight);

        //绘制我方坦克
        myTank.drawSelf(g);
        //获取子弹集合
        ArrayList<Shell> shellList=myTank.shelllist;
        for (int i = 0; i < otherTankList.size(); i++) {
             OtherTank otherTank=otherTankList.get(i);
             otherTank.drawSelf(g);
             //拿一个坦克和所有的炮弹做碰撞检测
            for (int j = 0; j < shellList.size(); j++) {
                Shell shell=shellList.get(j);
                //敌方对应矩形
                Rectangle rectOtherTank=otherTank.getRect();
                //子弹对应矩形
                Rectangle rectshell=shell.getRect();
                //检测碰撞
                boolean pen=rectOtherTank.intersects(rectshell);
                if (pen){
                    System.out.println("是否碰撞"+pen);
                    killCount++;
                    //炮弹命中了坦克
                    //炮弹消失，坦克消失
                    //需要结束此炮弹循环
                    otherTankList.remove(i);
                    shellList.remove(j);
                    //产生爆炸效果
                    Exploed exploed=new Exploed(null,otherTank.x,otherTank.y,otherTank.width+10,otherTank.height+10);
                    exploed.drawSelf(g);
                    //加载音频---固定格式
                    VoiceUtil.play("img/blast.wav");
                    break;
                }
            }
            Rectangle rectOtherTank =otherTank.getRect();
            Rectangle  rectMyTank=myTank.getRect();
            boolean pen1=rectMyTank.intersects(rectOtherTank);
            if(myTank.live){
                if(pen1){
                    //地方坦克消失
                    //我方坦克消失
                    //我方炮弹清零
                    //爆炸--我方和对方
                    //游戏结束
                    otherTankList.remove(i);
                    myTank.live=false;
                    shellList.clear();
                    Exploed exploed=new Exploed(null,otherTank.x,otherTank.y,otherTank.width+10,otherTank.height+10);
                    exploed.drawSelf(g);
                    Exploed exploed2=new Exploed(null,myTank.x,myTank.y,myTank.width+10,myTank.height+10);
                    exploed2.drawSelf(g);
                    killCount++;
                    //加载音频---固定格式
                    VoiceUtil.play("img/blast.wav");
                }
            }
        }
        g.setColor(Color.RED);
        g.drawString("杀敌数:"+killCount+"架",25,50);

    }

    //内部类
    class PaintThread extends Thread{
        public void run(){
            for (;;){
                repaint();//告知paint方法要执行
                try {
                    sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //双缓冲技术
    private Image offScreenImage=null;
    public void update(Graphics g){
        if(offScreenImage==null)
            offScreenImage=this.createImage(Constant.windowWidth, Constant.windowHeight);
        Graphics goff=offScreenImage.getGraphics();
        paint(goff);
        g.drawImage(offScreenImage,0,0,null);
      //需用fill覆盖


    }
    public static void main(String[] args) {
        new MyGameFrame01().init();
    }
   //用一个集合存放敌方坦克
    public ArrayList<OtherTank> otherTankList=new ArrayList<>(0);
    //再开一个线程---othertank
    class CreateOtherTank extends Thread{
       @Override
       public void run() {
           for (;;){
               //敌方坦克x范围：0---windowWidth-otherWidth 550
               int x=(int)(Math.random()*(Constant.windowWidth-Constant.otherWidth));
               BufferedImage image=ImageUtil.getImage("img/othertank.gif");
               OtherTank otherTank=new OtherTank(image,x,0,Constant.otherWidth,Constant.otherHeight,Constant.otherspeed);
              otherTankList.add(otherTank);
               try {
                   sleep(500);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
   }
}

