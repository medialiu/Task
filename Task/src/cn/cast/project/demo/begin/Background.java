package cn.cast.project.demo.begin;

import java.awt.image.BufferedImage;

import cn.cast.project.demo.util.ImageUtil;




public class Background {
    int x;
    int y;
    public Background(int x,int y) {
        // TODO Auto-generated constructor stub
        this.x=x;
        this.y=y;
    }

    private cn.cast.project.demo.util.ImageUtil ImageUtil;
    BufferedImage bg=ImageUtil.getImage("img/bg1.jpg");
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public BufferedImage getBg() {
        return bg;
    }
    public void move() {
        this.y+=55;
    }
    
}
