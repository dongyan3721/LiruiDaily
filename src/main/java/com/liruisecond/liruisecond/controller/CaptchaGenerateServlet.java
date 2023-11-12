package com.liruisecond.liruisecond.controller;

import com.alibaba.fastjson.JSON;
import com.liruisecond.liruisecond.entity.HttpCaptcha;
import com.liruisecond.liruisecond.service.CaptchaService;
import com.liruisecond.liruisecond.utils.CaptchaGenerator;
import com.liruisecond.liruisecond.utils.FourCharacterRandomStringGenerator;
import com.liruisecond.liruisecond.utils.common.HttpStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "captcha", value = "/captcha")
public class CaptchaGenerateServlet extends HttpServlet {
    private final CaptchaService captchaService = new CaptchaService();
    private final CaptchaGenerator captchaGenerator = new CaptchaGenerator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = FourCharacterRandomStringGenerator.generateRandomString(4);
        String href = captchaGenerator.base64FormattedCaptchaGenerate(value);
        String uuid = CaptchaGenerator.uuidProvide();
        System.out.println(captchaService.insertIntoCaptchaUUIDAndValue(uuid, value));
        HttpCaptcha captcha = new HttpCaptcha();
        captcha.setCode(HttpStatus.SUCCESS);
        captcha.setHref(href);
        captcha.setUuid(uuid);
        PrintWriter writer = resp.getWriter();
        writer.write(JSON.toJSONString(captcha));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        this.captchaService.closeConn();
    }
}
