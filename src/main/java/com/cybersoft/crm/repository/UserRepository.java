package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.entity.UserEntity;
import com.cybersoft.crm.model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
//Đối với câu lấy giá trị : Select => excuteQuery
//            insert,Update,Delete => excuteUpte
    public List<UserModel> getUsersByEmailAndPassword(String email, String password){
        List<UserModel> users = new ArrayList<>();
        try{
            String query = "select * from users u where u.email = ? and u.password = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,email);
            ps.setString(2,password);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setRoleName(rs.getString("name"));

                users.add(user);
            }

            connection.close();

        }catch (Exception e){
            System.out.println("Error getUsersByEmailAndPassword " + e.getMessage());
        }

        return users;
    }

    public List<UserModel> getUsers() {
        List<UserModel> users = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "SELECT u.id, u.email, u.password, u.firstname, u.lastname, r.name\n" +
                    "FROM users u\n" +
                    "INNER JOIN roles r\n" +
                    "ON u.role_id=r.id;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setRoleName(rs.getString("name"));

                users.add(user);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error get users " + e.getMessage());
        }
        return users;
    }

    public int deleteUserById(int id) {
        int result = 0;
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "delete from users u where u.id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            result = ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error delete user " + e.getMessage());
        }
        return result;
    }

    public int insertUser(UserEntity user) {
        int result = 0;
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "insert into users (firstname, lastname, email, password, role_id)\n" +
                    "values (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRoleId());
            result = ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error insert user " + e.getMessage());
        }
        return result;
    }

    public int updateUser(UserEntity user) {
        int result = 0;
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "update users set email=?, password=?, role_id=?, firstname=?, lastname=? where id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRoleId());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setInt(6, user.getId());
            result = ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error update user " + e.getMessage());
        }
        return result;
    }

    public UserEntity findUserById(int id) {
        UserEntity user = new UserEntity();
        try{
            String query = "select * from users where id = ?";
            Connection connection = MysqlConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRoleId(rs.getInt("role_id"));
            }

            connection.close();

        }catch (Exception e){
            System.out.println("Error findUserById " + e.getMessage());
        }

        return user;
    }
}
