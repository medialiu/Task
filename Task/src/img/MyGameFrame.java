package img;

import cn.cast.project.demo.begin.Constant;
import cn.cast.project.demo.util.ImageUtil;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class MyGameFrame extends Frame {
    int x = 200;
    //窗口初始化
    public void init() {
        setTitle("坦克大战");
        setSize(Constant.windowWidth, Constant.windowHeight);//大小
        setLocation(Constant.windowX, Constant.windowY);//位置
        setVisible(true);//窗口是否可见
        setResizable(true);//窗口是否可调整
        //监听事件并关闭java程序--匿名类
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //super.windowClosing(e);
                //监听到了就停止
                System.exit(0);
            }
        });
        new PaintThread().start();
    }
    //画图
    @Override
    public void paint(Graphics g) {
//        //画直线
//        g.drawLine(0,0,100,250);
//        //画矩形
//        g.drawRect(100,100,200,200);
//        //画圆---矩形内切圆
//        g.setColor(Color.CYAN);
//        g.drawOval(100,100,200,200);
//        g.fillRect(100,100,200,200);
//        //文本
//        g.setColor(Color.RED);
//        g.drawString("123",50,50);
        //加载图片
        g.fillRect(0,0,Constant.windowWidth, Constant.windowHeight);
        BufferedImage image=ImageUtil.getImage("img/1.jpg");
        try {
            int y = 200;
            int width = 200;
            int height = 200;
            ImageObserver observer = null;
            g.drawImage(image,x,y,width,height,observer);
            x+=3;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        new MyGameFrame().init();
    }


}
