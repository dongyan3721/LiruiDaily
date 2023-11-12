package com.liruisecond.liruisecond.utils;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;
import java.util.UUID;

public class CaptchaGenerator {
    private DefaultKaptcha captcha;

    public CaptchaGenerator() {
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, "150");
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, "40");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "32");
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "8");

        Config config = new Config(properties);
        kaptcha.setConfig(config);
        this.captcha = kaptcha;
    }

    public BufferedImage imageCaptchaGenerate(String text){
        return this.captcha.createImage(text);
    }

    public String base64FormattedCaptchaGenerate(String text){
        BufferedImage bufferedImage = this.captcha.createImage(text);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageBytes = outputStream.toByteArray();

        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public static String uuidProvide(){
        return UUID.randomUUID().toString();
    }
}
