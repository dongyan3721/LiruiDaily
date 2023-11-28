package com.liruisecond.liruisecond.controller;

import com.alibaba.fastjson.JSON;
import com.liruisecond.liruisecond.entity.HttpPermission;
import com.liruisecond.liruisecond.service.UserService;
import com.liruisecond.liruisecond.utils.common.HttpStatus;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class NoteLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origin = request.getHeader("Origin");

        // 检查是否来自5173端口
        if (origin != null && origin.endsWith(":5173")) {
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setContentType("application/json");

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserService userService = new UserService();
            if(userService.selectPasswordByUsername(username).equals(password)){
                HttpPermission httpPermission = new HttpPermission();
                httpPermission.setPermission("success");
                httpPermission.setCode(HttpStatus.SUCCESS);
                response.getWriter().write(JSON.toJSONString(httpPermission));
            }else{
                HttpPermission httpPermission = new HttpPermission();
                httpPermission.setPermission("forbidden");
                httpPermission.setCode(HttpStatus.FORBIDDEN);
                response.getWriter().write(JSON.toJSONString(httpPermission));
            }
        } else {
            // 不允许其他来源的请求
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
