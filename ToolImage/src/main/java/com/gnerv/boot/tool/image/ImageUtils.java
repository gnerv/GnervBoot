package com.gnerv.boot.tool.image;

import com.gnerv.boot.tool.image.config.ImagePosition;
import com.google.common.collect.Maps;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ImageUtils {

    public static final String IMAGE_TYPE_GIF = "gif";
    public static final String IMAGE_TYPE_JPG = "jpg";
    public static final String IMAGE_TYPE_JPEG = "jpeg";
    public static final String IMAGE_TYPE_BMP = "bmp";
    public static final String IMAGE_TYPE_PNG = "png";
    public static final String IMAGE_TYPE_PSD = "psd";

    private ImageUtils() {
    }

    public static BufferedImage insertLogo(BufferedImage source, BufferedImage logoImage) {
        Map<String, Integer> position = getPosition(source, ImagePosition.CENTER, 5);
        return drawImage(source, logoImage, position);
    }

    public static BufferedImage insertLogo(BufferedImage source, BufferedImage logoImage, ImagePosition imagePosition, int shrink) {
        Map<String, Integer> position = getPosition(source, imagePosition, shrink);
        return drawImage(source, logoImage, position);
    }

    public static BufferedImage insertLogo(BufferedImage source, BufferedImage logoImage, int x, int y, int width, int height, int shrink) {
        Map<String, Integer> position = getPosition(source, x, y, width, height, shrink);
        return drawImage(source, logoImage, position);
    }

    public static BufferedImage insertBackground(BufferedImage source, BufferedImage backgroundImage) {
        Map<String, Integer> position = getPosition(source, 0, 0, 320, 320, 1);
        return drawImage(backgroundImage, source, position);
    }

    private static BufferedImage drawImage(BufferedImage source, BufferedImage logoImage, Map<String, Integer> position) {
        BufferedImage bufferedImage=new BufferedImage(320, 320, BufferedImage.TYPE_INT_RGB);
        //图片缩略图实现
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.1f));
        graphics.drawImage(logoImage.getScaledInstance(320, 320, 4), 0, 0, null);
        graphics.drawImage(source, position.get("x"), position.get("y"), position.get("width"), position.get("height"), null);
        graphics.dispose();
        source.flush();
        return source;
    }

    private static Map<String, Integer> getPosition(BufferedImage source, int x, int y, int width, int height, int shrink) {
        Map<String, Integer> position = Maps.newHashMap();
        position.put("x", x);
        position.put("y", y);
        position.put("width", width);
        position.put("height", height);
        return position;
    }

    private static Map<String, Integer> getPosition(BufferedImage source, ImagePosition imagePosition, int shrink) {
        int width = source.getWidth();
        int height = source.getHeight();
        Map<String, Integer> position = Maps.newHashMap();
        position.put("x", width / shrink * 2);
        position.put("y", height / shrink * 2);
        position.put("width", width / shrink);
        position.put("height", height / shrink);
        switch (imagePosition) {
            case TOP:
                position.put("y", 0);
                break;
            case TOP_LEFT:
                position.put("x", 0);
                position.put("y", 0);
                break;
            case TOP_RIGHT:
                position.put("x", width - (width / shrink));
                position.put("y", 0);
                break;
            case BOTTOM:
                position.put("y", height - (height / shrink));
                break;
            case BOTTOM_LEFT:
                position.put("x", 0);
                position.put("y", height - (height / shrink));
                break;
            case BOTTOM_RIGHT:
                position.put("x", width - (width / shrink));
                position.put("y", height - (height / shrink));
                break;
            case CENTER_LEFT:
                position.put("x", 0);
                break;
            case CENTER_RIGHT:
                position.put("x", width - (width / shrink));
                break;
            default:
                break;
        }
        return position;
    }

    public static void main(String[] args) throws IOException {
//        BufferedImage backgroundImage = ImageIO.read(new File("H:\\2015081415025944.jpg"));
//        BufferedImage bufferedImage = insertBackground(source, backgroundImage);

        BufferedImage bufferedImage=new BufferedImage(320, 320, BufferedImage.TYPE_INT_RGB);

        BufferedImage source = ImageIO.read(new File("E:\\bg-start.jpg"));
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, 320, 320);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.9f));
        graphics.rotate(Math.toRadians(5));
        graphics.drawImage(source, 0, 0, 300, 300, null);

        ImageIO.write(bufferedImage, "png", new File("E:\\bg.png"));
    }
}
