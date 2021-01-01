package cn.cast.project.demo.begin;

import cn.cast.project.demo.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Exploed extends GameObject {
    public Exploed() {
    }
    public Exploed(Image image, int x, int y, int width, int height) {
        super(image, x, y, width, height);
    }
    //static  类
public static ArrayList<Image> imageArrayList=new ArrayList<>();
    //图片加载只执行一次
    static{
        BufferedImage image1=ImageUtil.getImage("img/blast1.gif");
        BufferedImage image2=ImageUtil.getImage("img/blast2.gif");
        BufferedImage image3=ImageUtil.getImage("img/blast3.gif");
        BufferedImage image4=ImageUtil.getImage("img/blast4.gif");
        BufferedImage image5=ImageUtil.getImage("img/blast5.gif");
        BufferedImage image6=ImageUtil.getImage("img/blast6.gif");
        BufferedImage image7=ImageUtil.getImage("img/blast7.gif");
        BufferedImage image8=ImageUtil.getImage("img/blast8.gif");
        imageArrayList.add(image1);
        imageArrayList.add(image2);
        imageArrayList.add(image3);
        imageArrayList.add(image4);
        imageArrayList.add(image5);
        imageArrayList.add(image6);
        imageArrayList.add(image7);
        imageArrayList.add(image8);
    }
    @Override
    public void drawSelf(Graphics graphics) {
        for (int i = 0; i < imageArrayList.size(); i++) {
            Image image=imageArrayList.get(i);
            graphics.drawImage(image,x,y,width,height,null);
        }
    }
}
