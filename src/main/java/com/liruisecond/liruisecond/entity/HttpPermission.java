package com.liruisecond.liruisecond.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpPermission {
    private Integer code;
    private String permission;
}
