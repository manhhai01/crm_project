package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginPage", urlPatterns = {"/login"})
public class LoginPage extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean isLogin = loginService.checkLogin(email,password);
        System.out.println("Kiem tra " + isLogin);

        if(isLogin){
            HttpSession session = req.getSession();
            session.setAttribute("isLogin",true);
            session.setMaxInactiveInterval(5 * 60);
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }

    }
}
