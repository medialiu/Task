package cn.cast.project.demo.begin;

import cn.cast.project.demo.util.ImageUtil;
import cn.cast.project.demo.util.VoiceUtil;
import cn.cast.project.demo.window.MyGameFrame01;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyTank extends GameObject{
    public int speed;//速度
    public boolean left,right,up,down;
    public boolean live=true;
    public MyTank() {
    }
    public MyTank(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height);
        this.speed = speed;//初始化坦克速度
        //当我方坦克产生时炮弹产生--->开启线程
        CreatShell creatShell=new CreatShell();
        creatShell.start();
    }
    //画自己
    @Override
    public void drawSelf(Graphics graphics) {
        if(live){//我方坦克存活时才画坦克
            super.drawSelf(graphics);//画坦克
            //接受键盘并移动加上边界检测
            if(left) {
                if (x >= 0 && x <= Constant.windowWidth - Constant.mytankWidth) {
                    x -= speed;
                } else if (x < 0) {
                    x = 0;
                } else if (x > Constant.windowWidth - Constant.mytankWidth) {
                    x = Constant.windowWidth - Constant.mytankWidth;
                }
            }
            if(right){
                if (x >= 0 && x <= Constant.windowWidth - Constant.mytankWidth) {
                    x += speed;
                } else if (x < 0) {
                    x = 0;
                } else if (x > Constant.windowWidth - Constant.mytankWidth) {
                    x = Constant.windowWidth - Constant.mytankWidth;
                }
            }
            if(up){
                if(y>=30&&y<=Constant.windowHeight-Constant.mytankHeight){
                    y-=speed;
                }else if(y<30){
                    y=30;
                }else if(y>Constant.windowHeight-Constant.mytankHeight){
                    y=Constant.windowHeight-Constant.mytankHeight;
                }
            }
            if(down){
                if(y>=30&&y<=Constant.windowHeight-Constant.mytankHeight){
                    y+=speed;
                }else if(y<30){
                    y=30;
                }else if(y>Constant.windowHeight-Constant.mytankHeight){
                    y=Constant.windowHeight-Constant.mytankHeight;
                }
            }
            //显示我方所有炮弹
            for (int i = 0; i < shelllist.size(); i++) {
                Shell shell=shelllist.get(i);
                //确定范围销毁炮弹
                if(shell.y<Constant.shellLive){
                    shelllist.remove(shell);
                }
                //画炮弹
                shell.drawSelf(graphics);
            }
        }

    }
    //控制键盘
    //左上右下37--40
    public void keypress(KeyEvent e){
         int keycode=e.getKeyCode();
         if(keycode==37){
             left=true;
         }else if(keycode==38){
             up=true;
         }else if(keycode==39){
             right=true;
         }else if(keycode==40){
             down=true;
         }
    }
    public void keyrest(KeyEvent e){
        int keycode=e.getKeyCode();
        if(keycode==37){
            left=false;
        }else if(keycode==38){
            up=false;
        }else if(keycode==39){
            right=false;
        }else if(keycode==40){
            down=false;
        }
    }
    //我方坦克拥有多个炮弹--》集合
    public ArrayList<Shell> shelllist=new ArrayList<>();


   class CreatShell extends Thread{
       @Override
       public void run() {

               for (;;){
                   if(live){
                   BufferedImage image= ImageUtil.getImage("img/shell.gif");
                   Shell shell=new Shell(image,x+Constant.mytankWidth/2-Constant.shellWidth/2,y,Constant.shellWidth,Constant.shellHeight,Constant.shellspeed);
                   shelllist.add(shell);
                   try {
                       //越小越密集
                       sleep(300);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   //加载音频---固定格式
                   VoiceUtil.play("img/fire.wav");
               }
           }
       }
   }




}
