package cn.cast.project.demo.begin;

import java.awt.*;

/**
 * 炮弹类
 */
public class Shell extends GameObject{
    public int speed;

    public Shell() {
    }

    public Shell(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height);
        this.speed = speed;
    }
    public void drawSelf(Graphics graphics) {
       super.drawSelf(graphics);
       y-=speed;
    }
}
