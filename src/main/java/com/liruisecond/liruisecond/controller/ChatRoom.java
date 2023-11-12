package com.liruisecond.liruisecond.controller;

import com.alibaba.fastjson.JSON;
import com.liruisecond.liruisecond.service.CaptchaService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="chatroom", value = "/chatroom")
public class ChatRoom extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        ArrayList<String> members = (ArrayList<String>)servletContext.getAttribute("members");
        PrintWriter writer = resp.getWriter();
        System.out.println(JSON.toJSONString(members));
        writer.write(JSON.toJSONString(members));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Map<String, String[]> parameterMap = req.getParameterMap();
        String name = null;
        String uuid = null;
        String value = null;
        try{
            name = parameterMap.get("user")[0];
            uuid = parameterMap.get("captcha-id")[0];
            value = parameterMap.get("captcha")[0];
            System.out.println(name);
            System.out.println(uuid);
            System.out.println(value);
        }catch (Exception e){
            req.getRequestDispatcher("./view/Login.jsp").forward(req, resp);
            return;
        }
        if(!checkCaptchaValid(value, uuid)){
            req.getRequestDispatcher("./view/Login.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession();
        session.setAttribute("name", name);
        if(servletContext.getAttribute("members")==null){
            ArrayList<String> members = new ArrayList<>();
            members.add("All");
            members.add(name);
            servletContext.setAttribute("members", members);
        }else{
            ArrayList<String> members = (ArrayList<String>)servletContext.getAttribute("members");
            if(members.contains(name)){
                req.getRequestDispatcher("./view/Login.jsp").forward(req, resp);
            }else{
                members.add(name);
                req.getRequestDispatcher("./view/ChatRoom.jsp").forward(req, resp);
            }
        }
    }

    private boolean checkCaptchaValid(String value, String uuid){
        CaptchaService captchaService = new CaptchaService();
        String correctValue = captchaService.getCaptchaValueByUUID(uuid);
        System.out.println(correctValue);
        captchaService.closeConn();
        return value.equalsIgnoreCase(correctValue);
    }

}
