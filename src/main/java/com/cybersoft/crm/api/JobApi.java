package com.cybersoft.crm.api;

import com.cybersoft.crm.entity.JobEntity;
import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.service.JobService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "jobApi", urlPatterns = {"/api/job/add", "/api/job/delete", "/api/job/update"})
public class JobApi extends HttpServlet {
    private JobService jobService = new JobService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = jobService.deleteJobById(id);

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
            case "/api/job/add":
                String nameAdd = req.getParameter("name");
                String startDateAdd = req.getParameter("startDate");
                String endDateAdd = req.getParameter("endDate");
                JobEntity jobAdd = new JobEntity(nameAdd, startDateAdd, endDateAdd);
                boolean isSuccessAdd = jobService.insertJob(jobAdd);
                responseData.setStatus(200);
                responseData.setSuccess(isSuccessAdd);
                responseData.setDescription(isSuccessAdd ? "Thêm thành công" : "Thêm thất bại");
                break;
            case "/api/job/update":
                int id = Integer.parseInt(req.getParameter("id"));
                String nameUpdate = req.getParameter("name");
                String startDateUpdate = req.getParameter("startDate");
                String endDateUpdate = req.getParameter("endDate");
                JobEntity jobUpdate = new JobEntity(id, nameUpdate, startDateUpdate, endDateUpdate);
                boolean isSuccessUpdate = jobService.updateJob(jobUpdate);
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
