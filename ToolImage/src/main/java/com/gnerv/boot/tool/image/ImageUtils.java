package com.gnerv.boot.tool.image;

import com.gnerv.boot.tool.image.config.ImagePosition;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageUtils {

    private ImageUtils() {
    }

    public static BufferedImage insertLogo(BufferedImage source, BufferedImage logoImage) {
        Map<String, Integer> position = getPosition(source, ImagePosition.CENTER, 5);
        BufferedImage drawImage = drawImage(source, logoImage, position);
        return drawImage;
    }

    public static BufferedImage insertLogo(BufferedImage source, BufferedImage logoImage, ImagePosition imagePosition, int shrink) {
        Map<String, Integer> position = getPosition(source, imagePosition, shrink);
        BufferedImage drawImage = drawImage(source, logoImage, position);
        return drawImage;
    }

    public static BufferedImage insertLogo(BufferedImage source, BufferedImage logoImage, int x, int y, int width, int height, int shrink) {
        Map<String, Integer> position = getPosition(source, x, y, width, height, shrink);
        BufferedImage drawImage = drawImage(source, logoImage, position);
        return drawImage;
    }

    public static BufferedImage insertBackground(BufferedImage source, BufferedImage backgroundImage) {
        Map<String, Integer> position = getPosition(source, 0, 0, 320, 320, 1);
        BufferedImage drawImage = drawImage(backgroundImage, source, position);
        return drawImage;
    }

    private static BufferedImage drawImage(BufferedImage source, BufferedImage logoImage, Map<String, Integer> position) {
        Graphics2D graphics = source.createGraphics();
        graphics.drawImage(logoImage, position.get("x"), position.get("y"), position.get("width"), position.get("height"), null);
        graphics.dispose();
        source.flush();
        return source;
    }

    private static Map getPosition(BufferedImage source, int x, int y, int width, int height, int shrink) {
        Map<String, Integer> position = new HashMap<String, Integer>(8);
        position.put("x", x);
        position.put("y", y);
        position.put("width", width);
        position.put("height", height);
        return position;
    }

    private static Map getPosition(BufferedImage source, ImagePosition imagePosition, int shrink) {
        int width = source.getWidth();
        int height = source.getHeight();
        Map<String, Integer> position = new HashMap<String, Integer>(8);
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

}
