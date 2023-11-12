package com.liruisecond.liruisecond.controller;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@WebServlet(name = "download", value = "/download3")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        String op1 = req.getParameter("op1");
        String op2 = req.getParameter("op2");
        String op3 = req.getParameter("op3");
        if(op1!=null){
            respFile(req, resp, "双创4621-图文制作清单-(1).xlsx");
        }else if(op2!=null){
            respFile(req, resp, "双创---4709-图文制作清单-(1).xlsx");
        }else if(op3!=null){
            respFile(req, resp, "Word文件.docx");
        }
    }

    private void respFile(HttpServletRequest req, HttpServletResponse resp, String filePath) throws IOException {
        ServletContext context = getServletContext();
        InputStream resourceAsStream = context.getResourceAsStream("/static/"+filePath);
        String mimeType = context.getMimeType("/static/"+filePath);
        resp.setContentType(mimeType);
        resp.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(filePath, "utf-8"));
        byte[] arr = new byte[1024 * 8];
        ServletOutputStream os = resp.getOutputStream();
        int read = 0;
        while(read!=-1){
            read = resourceAsStream.read(arr);
            os.write(arr, 0, read);
        }
    }
}
