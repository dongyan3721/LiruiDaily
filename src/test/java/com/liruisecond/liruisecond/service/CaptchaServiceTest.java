package com.liruisecond.liruisecond.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaptchaServiceTest {

    @Test
    void insertIntoCaptchaUUIDAndValue() {
        System.out.println(new CaptchaService().getCaptchaValueByUUID("122"));
    }

    @Test
    void captchaValueByUUID() {
        System.out.println(new CaptchaService().insertIntoCaptchaUUIDAndValue("9089-8378", "YU80"));
    }
}