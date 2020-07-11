package com.example.term.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//图片添加水印
public class PhotoUtil {
    private final static Font FONT = new Font("微软雅黑", Font.ITALIC, 12);
    private static final Color COLOR = new Color(255, 255, 255);
    private static final int WATER_POSITION_X = 5;
    private static final int WATER_POSITION_Y = 15;

    public static void addWaterMark(String filePath, LocalDateTime time, String username, String position){
        String format = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String content = format + " " + username;
        try {
            File file = new File(filePath);
            BufferedImage bufImage = ImageIO.read(file);
            int width = bufImage.getWidth();
            int height = bufImage.getHeight();
            BufferedImage waterMark = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
            Graphics2D g = waterMark.createGraphics();
            g.drawImage(bufImage, 0, 0, width, height, null);
            g.setColor(COLOR); //水印颜色
            g.setFont(FONT); //水印字体
            g.drawString(content, WATER_POSITION_X, WATER_POSITION_Y);
            g.drawString(position, WATER_POSITION_X, WATER_POSITION_Y + 20);
            g.dispose();    // 释放资源
            FileOutputStream fos = new FileOutputStream(filePath);
            ImageIO.write(waterMark, "png", fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
