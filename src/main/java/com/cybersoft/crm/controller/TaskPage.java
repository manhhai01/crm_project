package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;
import com.cybersoft.crm.service.StatusService;
import com.cybersoft.crm.service.TaskService;
import com.cybersoft.crm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "taskPage", urlPatterns = {"/task", "/task-add", "/task-update"})
public class TaskPage extends HttpServlet {

    private TaskService taskService = new TaskService();
    private JobService jobService = new JobService();
    private UserService userService = new UserService();
    private StatusService statusService = new StatusService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/task":
                req.setAttribute("tasks", taskService.getAllTasks());
                req.getRequestDispatcher("/task.jsp").forward(req, resp);
                break;
            case "/task-add":
                req.getSession().setAttribute("jobs", jobService.getAllJobs());
                req.getSession().setAttribute("users", userService.getAllUsers());
                req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
                break;
            case "/task-update":
                req.getSession().setAttribute("jobs", jobService.getAllJobs());
                req.getSession().setAttribute("users", userService.getAllUsers());
                req.getRequestDispatcher("/task-update.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/task-update":
                int id = Integer.parseInt(req.getParameter("id"));
                req.getSession().setAttribute("jobs", jobService.getAllJobs());
                req.getSession().setAttribute("users", userService.getAllUsers());
                req.getSession().setAttribute("statuses", statusService.getAllStatuses());
                req.getSession().setAttribute("task", taskService.findTaskById(id));
                req.getRequestDispatcher("/task-update.jsp").forward(req,resp);
                break;
        }
    }
}
