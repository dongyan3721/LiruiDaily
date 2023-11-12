package com.liruisecond.liruisecond.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class FormContent {
    private String name;
    private String age;
    private String gender;
    private ArrayList<String> hobbies;
}
