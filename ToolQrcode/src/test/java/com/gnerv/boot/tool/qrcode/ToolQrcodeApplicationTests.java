package com.gnerv.boot.tool.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ToolQrcodeApplicationTests {

    @Test
    void contextLoads() {
        String s = "16:24:26.015 [main] DEBUG org.springframework.test.context.BootstrapUtils - Instantiating CacheAwareContextLoaderDelegate from class [org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate]\n" +
                " :: Spring Boot ::        (v2.2.2.RELEASE)\n";
        try {
            BitMatrix encode = encode(s);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(encode);
            ImageIO.write(bufferedImage, "png", new File("E:\\1232.png"));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

    }

    private BitMatrix encode(String contents) throws WriterException {
        final Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix encode;
        encode = new QRCodeWriter().encode(contents, BarcodeFormat.QR_CODE, 320, 320);
//        encode = new MultiFormatWriter().encode(contents, BarcodeFormat.DATA_MATRIX, 320, 80);
//        encode = new DataMatrixWriter().encode(contents, BarcodeFormat.DATA_MATRIX, 320, 80);
//        encode = new PDF417Writer().encode(contents, BarcodeFormat.PDF_417, 320, 80);
        return encode;
    }


}
