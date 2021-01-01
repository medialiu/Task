package cn.cast.project.demo.begin;

import java.awt.*;

public class OtherTank extends GameObject {
    public int speed;

    public OtherTank() {
    }

    public OtherTank(Image image, int x, int y, int width, int height, int speed) {
        super(image, x, y, width, height);
        this.speed = speed;
    }
    @Override
    public void drawSelf(Graphics graphics) {
        super.drawSelf(graphics);
        y+=speed;
    }
}
