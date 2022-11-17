package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.RoleService;
import com.cybersoft.crm.service.TaskService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "userPage", urlPatterns = {"/user", "/user-add", "/user-update", "/user-detail"})
public class UserPage extends HttpServlet {

    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();
    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/user":
                req.setAttribute("users",userService.getAllUsers());
                req.getRequestDispatcher("/user-table.jsp").forward(req,resp);
                break;
            case "/user-add":
                req.setAttribute("roles", roleService.getAllRoles());
                req.getRequestDispatcher("/user-add.jsp").forward(req,resp);
                break;
            case "/user-update":
                req.setAttribute("roles", roleService.getAllRoles());
                req.getRequestDispatcher("/user-update.jsp").forward(req,resp);
                break;
            case "/user-detail":
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("user", userService.findUserById(id));
                req.setAttribute("tasks", taskService.findTasksByUserId(id));
                req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/user-update":
                int id = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("roles", roleService.getAllRoles());
                req.getSession().setAttribute("user", userService.findUserById(id));
                req.getRequestDispatcher("/user-update.jsp").forward(req,resp);
                break;
        }
    }
}
