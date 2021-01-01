package cn.cast.project.demo.begin;

import java.awt.*;

/**
 * 游戏对象
 */
public class GameObject {
    public Image image;
    public int x;
    public int y;
    public int width;
    public int height;

    public GameObject() {
    }

    public GameObject(Image image, int x, int y, int width, int height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
  //画自己
    public void drawSelf(Graphics graphics) {
        graphics.drawImage(image, x, y, width, height, null);
    }
    //获取矩形对象
    public Rectangle getRect(){
        return new Rectangle(x,y,width,height);
    }



}
