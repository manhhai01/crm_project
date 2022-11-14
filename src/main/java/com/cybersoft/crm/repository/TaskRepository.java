package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public List<TaskModel> getRoles() {
        List<TaskModel> list = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "select * from tasks";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                TaskModel task = new TaskModel();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setStartDate(rs.getString("start_date"));
                task.setEndDate(rs.getString("end_date"));
                task.setUserId(rs.getInt("user_id"));
                task.setJobId(rs.getInt("job_id"));
                task.setStatusId(rs.getInt("status_id"));

                list.add(task);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error get tasks " + e.getMessage());
        }
        return list;
    }
}
