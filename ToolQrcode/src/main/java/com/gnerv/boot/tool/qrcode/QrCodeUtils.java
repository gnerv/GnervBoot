package com.gnerv.boot.tool.qrcode;

import com.gnerv.boot.tool.image.ImageUtils;
import com.gnerv.boot.tool.image.config.ImagePosition;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.apache.catalina.manager.Constants.CHARSET;

@Slf4j
public class QrCodeUtils {

    private QrCodeUtils() {
    }

    public static BufferedImage encode(String content) {
        int width = 320;
        int height = 320;
        try {
            BitMatrix encode = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, createHints());
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(encode);
            return bufferedImage;
        } catch (WriterException e) {
            log.error("二维码生成失败", e);
        }
        return null;
    }

    public static BufferedImage encode(String content, BufferedImage logoImage) {
        BufferedImage bufferedImage = encode(content);
        bufferedImage = ImageUtils.insertLogo(bufferedImage, logoImage, ImagePosition.CENTER, 5);
        return bufferedImage;
    }

    public static BufferedImage encode(String content, BufferedImage logoImage, ImagePosition imagePosition) {
        BufferedImage bufferedImage = encode(content);
        bufferedImage = ImageUtils.insertLogo(bufferedImage, logoImage, imagePosition, 5);
        return bufferedImage;
    }

    public static BufferedImage encode(String content, BufferedImage logoImage, ImagePosition imagePosition, int shrink) {
        BufferedImage bufferedImage = encode(content);
        bufferedImage = ImageUtils.insertLogo(bufferedImage, logoImage, imagePosition, shrink);
        return bufferedImage;
    }





    private static Map createHints() {
        Map hints = new HashMap();
        // 容错级别 H->30%
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        return hints;
    }

}
