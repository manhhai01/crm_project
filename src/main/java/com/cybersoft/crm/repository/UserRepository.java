package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.entity.UserEntity;
import com.cybersoft.crm.model.RoleModel;
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
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                UserModel user = new UserModel();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setRoleName(resultSet.getString("name"));

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
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

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
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();

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
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRoleId());
            result = preparedStatement.executeUpdate();

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
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getRoleId());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setInt(6, user.getId());
            result = preparedStatement.executeUpdate();

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
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstname"));
                user.setLastName(resultSet.getString("lastname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRoleId(resultSet.getInt("role_id"));
            }

            connection.close();

        }catch (Exception e){
            System.out.println("Error findUserById " + e.getMessage());
        }

        return user;
    }
}
