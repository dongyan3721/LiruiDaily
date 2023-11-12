package com.liruisecond.liruisecond.controller;

import com.alibaba.fastjson.JSON;
import com.liruisecond.liruisecond.entity.Message;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name="accept", value = "/accept")
public class MessageAccept extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        ArrayList<Message> messages = (ArrayList<Message>)getServletContext().getAttribute("messages");
        writer.write(JSON.toJSONString(messages));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Map<String, String[]> parameterMap = req.getParameterMap();



        String userSel = parameterMap.get("userSel")[0];
        String msg = parameterMap.get("msg")[0];
        String origin = parameterMap.get("origin")[0];
        Message message = new Message();
        message.setFrom(origin);
        message.setMsg(msg);
        message.setTo(userSel);
        ServletContext servletContext = getServletContext();
        if(servletContext.getAttribute("messages")==null){
            ArrayList<Message> messages = new ArrayList<>();
            messages.add(message);
            servletContext.setAttribute("messages", messages);
        }else{
            ArrayList<Message> messages = (ArrayList<Message>)servletContext.getAttribute("messages");
            messages.add(message);
        }
    }
}
