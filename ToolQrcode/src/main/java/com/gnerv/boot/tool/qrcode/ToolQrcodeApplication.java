package com.gnerv.boot.tool.qrcode;

import com.gnerv.boot.tool.image.ImageUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@SpringBootApplication
@RestController
public class ToolQrcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToolQrcodeApplication.class, args);
    }

    @RequestMapping("getQrCode/{type}/{content}")
    public void getQrCode(@PathVariable(value = "type", required = false) int type, @PathVariable(value = "content", required = false) String content, HttpServletResponse response){
        BufferedImage bufferedImage = QrCodeUtils.encode(content);
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\lgnerv\\Pictures\\3.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferedImage = ImageUtils.insertBackground(bufferedImage, backgroundImage);
        try {
            //设置文件名和编码
            String fileName = URLEncoder.encode("demo.png", "UTF-8");
            if(type == 1){
                response.setHeader("Content-Disposition", "inline;filename=" + fileName);
            }else {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            }
            ImageIO.write(bufferedImage, "png", response.getOutputStream());
//            FileCopyUtils.copy(new FileInputStream(new File("C:\\Users\\ligen\\Pictures\\Camera Roll\\demo.mp4")), response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
