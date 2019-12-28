package com.gnerv.boot.tool.qrcode;

import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.LuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Slf4j
/**
 * 二维码生成器
 *
 * @author zhang
 * @date 2019/12/1
 **/
public class QrCodeUtils {

    private QrCodeUtils() {
    }

    public static BufferedImage encode(String content) {
        return encode(content, 320, 320);
    }

    public static BufferedImage encode(String content, int width, int height) {
        Map<EncodeHintType, Object> hints = Maps.newHashMap();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
        try {
            BitMatrix encode = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            return MatrixToImageWriter.toBufferedImage(encode);
        } catch (WriterException e) {
            log.error("二维码生成失败", e);
        }
        return null;
    }

    public static Result decode(BufferedImage bufferedImage) {
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
        return decode(binaryBitmap);
    }

    public static Result decode(BinaryBitmap binaryBitmap) {
        Map<DecodeHintType, Object> hints = Maps.newHashMap();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        try {
            return new QRCodeReader().decode(binaryBitmap, hints);
        } catch (NotFoundException | ChecksumException | FormatException e) {
            log.error("二维码解析失败", e);
        }
        return null;
    }

}
