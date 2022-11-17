package com.cybersoft.crm.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "homePage",urlPatterns = {"/home", "/blank", "/error"})
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/home":
                req.getRequestDispatcher("/index.html").forward(req,resp);
                break;
            case "/blank":
                req.getRequestDispatcher("/blank.html").forward(req,resp);
                break;
            case "/error":
                req.getRequestDispatcher("/404.html").forward(req,resp);
                break;
        }
    }
}
