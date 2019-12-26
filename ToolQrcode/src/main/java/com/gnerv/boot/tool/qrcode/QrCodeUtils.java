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

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.apache.catalina.manager.Constants.CHARSET;

public class QrCodeUtils {

    private QrCodeUtils() {
    }

    public static BufferedImage encode(String content) {
        int i = new Random().nextInt();
        int width = 320;
        int height = 320;
        BufferedImage bufferedImage;
        try {
            BitMatrix encode = new QRCodeWriter().encode(content + i, BarcodeFormat.QR_CODE, width, height, createHints());
            bufferedImage = MatrixToImageWriter.toBufferedImage(encode);
            bufferedImage = ImageUtils.insertLogo(bufferedImage, null, ImagePosition.BOTTOM_LEFT, 5);
            return bufferedImage;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
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
