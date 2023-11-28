package com.liruisecond.liruisecond.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService service = new UserService();

    @Test
    void insertIntoUsers() {
        System.out.println(service.insertIntoUsers("你爹", "123456"));
    }

    @Test
    void selectPasswordByUsername() {
        System.out.println(service.selectPasswordByUsername("你爹"));
    }

    @Test
    void closeConn() {
        service.closeConn();
    }
}