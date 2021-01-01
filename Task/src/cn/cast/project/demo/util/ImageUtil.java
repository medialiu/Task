package cn.cast.project.demo.util;
import cn.cast.project.demo.window.MyGameFrame01;
import img.MyGameFrame;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
//加载图片工具类
public class ImageUtil {
    public static BufferedImage getImage(String path){
        URL url = MyGameFrame01.class.getClassLoader().getResource(path);
        try {
            BufferedImage image= ImageIO.read(url);
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
