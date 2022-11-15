package com.cybersoft.crm.api;

import com.cybersoft.crm.entity.TaskEntity;
import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.service.TaskService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "taskApi", urlPatterns = {"/api/task/delete", "/api/task/add","/api/task/update"})
public class TaskApi extends HttpServlet {

    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = taskService.deleteTaskById(id);

        ResponseData responseData = new ResponseData();
        responseData.setStatus(200);
        responseData.setSuccess(isSuccess);
        responseData.setDescription(isSuccess ? "Xóa thành công" : "Xóa thất bại");

        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        ResponseData responseData = new ResponseData();
        String action = req.getServletPath();
        switch (action) {
            case "/api/task/add":
                String nameAdd = req.getParameter("name");
                String startDateAdd = req.getParameter("startDate");
                String endDateAdd = req.getParameter("endDate");
                int userId = Integer.parseInt(req.getParameter("userId"));
                int jobId = Integer.parseInt(req.getParameter("jobId"));
                TaskEntity taskAdd = new TaskEntity(nameAdd, startDateAdd, endDateAdd, userId, jobId);
                boolean isSuccessAdd = taskService.insertTask(taskAdd);
                responseData.setStatus(200);
                responseData.setSuccess(isSuccessAdd);
                responseData.setDescription(isSuccessAdd ? "Thêm thành công" : "Thêm thất bại");
                break;
            case "/api/task/update":
                int id = Integer.parseInt(req.getParameter("id"));
                String nameUpdate = req.getParameter("name");
                String startDateUpdate = req.getParameter("startDate");
                String endDateUpdate = req.getParameter("endDate");
                int userIdUpdate = Integer.parseInt(req.getParameter("userId"));
                int jobIdUpdate = Integer.parseInt(req.getParameter("jobId"));
                int statusIdUpdate = Integer.parseInt(req.getParameter("statusId"));

                TaskEntity taskUpdate = new TaskEntity(id, nameUpdate, startDateUpdate, endDateUpdate, userIdUpdate, jobIdUpdate, statusIdUpdate);

                boolean isSuccessUpdate = taskService.updateTask(taskUpdate);
                responseData.setStatus(200);
                responseData.setSuccess(isSuccessUpdate);
                responseData.setDescription(isSuccessUpdate ? "Cập nhật thành công" : "Cập nhật thất bại");
                break;
        }

        Gson gson = new Gson();
        String json = gson.toJson(responseData);

        out.print(json);
        out.flush();
    }
}
