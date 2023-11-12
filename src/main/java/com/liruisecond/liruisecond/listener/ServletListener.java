package com.liruisecond.liruisecond.listener;

import com.liruisecond.liruisecond.entity.Message;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.ArrayList;

@WebListener
public class ServletListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String id = se.getSession().getId();
        System.out.println("session: "+id+" was created!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        System.out.println("session: "+session.getId()+" was destroyed!");
        if(context.getAttribute("message")!=null&&session.getAttribute("origin")!=null){
            ArrayList<Message> messages = (ArrayList<Message>)context.getAttribute("messages");
            Message message = new Message();
            message.setTo("All");
            message.setFrom((String) session.getAttribute("origin"));
            message.setMsg("我走辣");
            messages.add(message);
        }
    }
}
