package com.liruisecond.liruisecond.controller;

import com.alibaba.fastjson.JSON;
import com.liruisecond.liruisecond.entity.Message;
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
import java.util.Arrays;
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
//        System.out.println(req.getHeader("Content-Type"));
        Map<String, String[]> map = req.getParameterMap();
        map.forEach((key, value)->{
            System.out.println(key+"-"+Arrays.toString(value));
        });
        HttpSession session = req.getSession(false);
        if(session==null){
            session = req.getSession();
        }
        if(req.getParameter("first")!=null&&session.getAttribute("isFirst")==null){
            addMessage(req, resp, req.getParameter("origin"), req.getParameter("msg"), req.getParameter("userSel"));
            session.setAttribute("isFirst", false);
            return;
        }
        if(session.getAttribute("leave")!=null){
            System.out.println(session.getAttribute("leave"));
            session.invalidate();
            req.getRequestDispatcher("/view/Login.jsp");
            return;
        }
        addMessage(req, resp, req.getParameter("origin"), req.getParameter("msg"), req.getParameter("userSel"));
    }

    private void addMessage(HttpServletRequest req, HttpServletResponse resp, String origin, String msg, String userSel){
        req.getSession().setAttribute("origin", origin);
        System.out.println("origin"+origin);
        System.out.println("msg"+msg);
        System.out.println("userSel"+userSel);
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
