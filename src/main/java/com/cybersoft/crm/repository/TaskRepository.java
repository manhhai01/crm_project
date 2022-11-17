package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.entity.TaskEntity;
import com.cybersoft.crm.model.TaskModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public List<TaskModel> getTasks() {
        List<TaskModel> list = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "select t.id, t.name as task_name, j.name as job_name, u.firstname, u.lastname, t.start_date, t.end_date, s.name as status_name\n" +
                    "from tasks t inner join users u on t.user_id = u.id\n" +
                    "inner join status s on t.status_id = s.id\n" +
                    "inner join jobs j on t.job_id = j.id";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                TaskModel task = new TaskModel();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("task_name"));
                task.setStartDate(rs.getString("start_date"));
                task.setEndDate(rs.getString("end_date"));
                task.setJobName(rs.getString("job_name"));
                task.setUserName(rs.getString("firstname") + " " + rs.getString("lastname"));
                task.setStatusName(rs.getString("status_name"));

                list.add(task);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error get tasks " + e.getMessage());
        }
        return list;
    }

    public int deleteTaskById(int id) {
        int result = 0;
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "delete from tasks t where t.id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            result = ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error delete task " + e.getMessage());
        }
        return result;
    }

    public int insertTask(TaskEntity task) {
        int result = 0;
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "insert into tasks (name, start_date, end_date, user_id, job_id, status_id)\n" +
                    "values (?, ?, ?, ?, ?, 1)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, task.getName());
            ps.setString(2, task.getStartDate());
            ps.setString(3, task.getEndDate());
            ps.setInt(4, task.getUserId());
            ps.setInt(5, task.getJobId());
            result = ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error insert task " + e.getMessage());
        }
        return result;
    }

    public TaskEntity findTaskById(int id) {
        TaskEntity task = new TaskEntity();
        try{
            String query = "select * from tasks where id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setStartDate(rs.getString("start_date"));
                task.setEndDate(rs.getString("end_date"));
                task.setUserId(rs.getInt("user_id"));
                task.setJobId(rs.getInt("job_id"));
                task.setStatusId(rs.getInt("status_id"));
            }

            connection.close();

        }catch (Exception e){
            System.out.println("Error findTaskById " + e.getMessage());
        }

        return task;
    }

    public int updateTask(TaskEntity task) {
        int result = 0;
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "update tasks set name=?, start_date=?, end_date=?, user_id=?, job_id=?, status_id=? where id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, task.getName());
            ps.setString(2, task.getStartDate());
            ps.setString(3, task.getEndDate());
            ps.setInt(4, task.getUserId());
            ps.setInt(5, task.getJobId());
            ps.setInt(6, task.getStatusId());
            ps.setInt(7, task.getId());
            result = ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error update task " + e.getMessage());
        }
        return result;
    }

    public List<TaskEntity> findTaskByUserId(int userId) {
        List<TaskEntity> tasks = new ArrayList<>();
        try{
            String query = "select * from tasks where user_id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                TaskEntity task = new TaskEntity();
                task.setId(rs.getInt("id"));
                task.setName(rs.getString("name"));
                task.setStartDate(rs.getString("start_date"));
                task.setEndDate(rs.getString("end_date"));
                task.setUserId(rs.getInt("user_id"));
                task.setJobId(rs.getInt("job_id"));
                task.setStatusId(rs.getInt("status_id"));

                tasks.add(task);
            }

            connection.close();

        }catch (Exception e){
            System.out.println("Error findTaskByUserId " + e.getMessage());
        }

        return tasks;
    }
}
