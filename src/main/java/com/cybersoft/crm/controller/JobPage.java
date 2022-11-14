package com.cybersoft.crm.controller;

import com.cybersoft.crm.service.JobService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "groupwork", urlPatterns = {"/groupwork", "/groupwork-add", "/groupwork-update"})
public class JobPage extends HttpServlet {

    private JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getServletPath();
        switch (action) {
            case "/groupwork":
                req.setAttribute("jobs", jobService.getAllJobs());
                req.getRequestDispatcher("/groupwork.jsp").forward(req,resp);
                break;
            case "/groupwork-add":
                req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
                break;
            case "/groupwork-update":
                req.getRequestDispatcher("/groupwork-update.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/groupwork-update":
                int id = Integer.parseInt(req.getParameter("id"));
                req.getSession().setAttribute("job", jobService.findJobById(id));
                req.getRequestDispatcher("/user-update.jsp").forward(req,resp);
                break;
        }
    }
}
