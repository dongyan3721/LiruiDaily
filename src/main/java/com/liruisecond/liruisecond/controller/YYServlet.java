package com.liruisecond.liruisecond.controller;

import com.alibaba.fastjson2.JSON;
import com.liruisecond.liruisecond.entity.FormContent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet(name="yyServlet", value="/yy-servlet")
public class YYServlet extends HttpServlet {

    private HashMap<String, FormContent> memoryContent = new HashMap<>();
    private int manNum, read = 0, swim = 0, travel = 0, game = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        /**
        Set<String> keySet = parameterMap.keySet();
        for (String key : keySet) {

            String[] values = parameterMap.get(key);

            System.out.println(key + "=" + Arrays.asList(values));
        }*/
        FormContent content = new FormContent();
        Date date = new Date();
        content.setAge(parameterMap.get("age")[0].equals("")?"保密":parameterMap.get("age")[0]);
        String gender = parameterMap.get("gender")[0];
        if(gender.equals("男")){
            ++this.manNum;
        }
        content.setGender(gender);
        content.setName(parameterMap.get("user")[0].equals("")?"未知用户"+date.getTime():parameterMap.get("user")[0]);
        if(parameterMap.containsKey("hobby")){
            content.setHobbies(new ArrayList<>(Arrays.asList(parameterMap.get("hobby"))));
            for(String s:parameterMap.get("hobby")){
                if(s.equals("看书")){
                    ++this.read;
                }else if(s.equals("旅游")){
                    ++this.travel;
                }else if(s.equals("体育")){
                    ++this.swim;
                }else if(s.equals("游戏")){
                    ++this.game;
                }
            }
        }else{
            content.setHobbies(new ArrayList<>());
        }
        this.memoryContent.put(content.getName(), content);
        req.getSession().setAttribute("content", this.memoryContent);
        req.getSession().setAttribute("contStr", JSON.toJSON(this.memoryContent));
        req.setAttribute("total", this.memoryContent.size());
        req.setAttribute("man", this.manNum);
        req.setAttribute("women", this.memoryContent.size()-this.manNum);
        req.setAttribute("pe", this.swim);
        req.setAttribute("game", this.game);
        req.setAttribute("travel",this.travel);
        req.setAttribute("read", this.read);
        req.getRequestDispatcher("./view/DetailedQuestionareInfo.jsp").forward(req, resp);
//        resp.sendRedirect("./view/DetailedQuestionareInfo.jsp");
    }
}
