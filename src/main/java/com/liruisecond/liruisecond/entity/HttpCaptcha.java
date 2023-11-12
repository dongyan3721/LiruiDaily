package com.liruisecond.liruisecond.entity;

import lombok.Data;

@Data
public class HttpCaptcha {
    int code;
    String uuid;
    String href;
}
