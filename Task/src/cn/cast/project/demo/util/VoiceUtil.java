package cn.cast.project.demo.util;

import cn.cast.project.demo.window.MyGameFrame01;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.IOException;
import java.io.InputStream;

public class VoiceUtil {
    public static void play(String path){
        //加载音频---固定格式
        InputStream inputStream= MyGameFrame01.class.getClassLoader().getResourceAsStream(path);
        try {
            AudioStream audioStream=new AudioStream(inputStream);
            AudioPlayer.player.start(audioStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
