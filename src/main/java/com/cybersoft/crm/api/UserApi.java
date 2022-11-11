package com.cybersoft.crm.api;

import com.cybersoft.crm.entity.UserEntity;
import com.cybersoft.crm.payload.ResponseData;
import com.cybersoft.crm.service.UserService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "userApi", urlPatterns = {"/api/user/delete","/api/user/add", "/api/user/update"})
public class UserApi extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        boolean isSuccess = userService.deleteUserById(id);

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
            case "/api/user/add":
                String firstNameAdd = req.getParameter("firstname");
                String lastNameAdd = req.getParameter(("lastname"));
                String emailAdd =  req.getParameter("email");
                String passwordAdd = req.getParameter("password");
                int roleIdAdd = Integer.parseInt(req.getParameter("roleId"));
                UserEntity userAdd = new UserEntity(emailAdd, passwordAdd, firstNameAdd, lastNameAdd, roleIdAdd);
                boolean isSuccessAdd = userService.insertUser(userAdd);
                responseData.setStatus(200);
                responseData.setSuccess(isSuccessAdd);
                responseData.setDescription(isSuccessAdd ? "Thêm thành công" : "Thêm thất bại");
                break;
            case "/api/user/update":
                int id = Integer.parseInt(req.getParameter("id"));
                String firstname = req.getParameter("firstname");
                String lastname = req.getParameter("lastname");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                int roleId = Integer.parseInt(req.getParameter("roleId"));

                UserEntity userUpdate = new UserEntity(id, email, password, firstname, lastname, roleId);

                boolean isSuccessUpdate = userService.updateUser(userUpdate);
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
