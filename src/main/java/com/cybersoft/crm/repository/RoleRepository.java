package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.model.RoleModel;

import javax.management.relation.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {

    public List<RoleModel> getRoles() {
        List<RoleModel> roles = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "select * from roles";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                RoleModel role = new RoleModel();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("description"));

                roles.add(role);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error get roles " + e.getMessage());
        }
        return roles;
    }

    public int deleteRoleById(int id) {
        int result = 0;
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "delete from roles r where r.id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            result = ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error delete role " + e.getMessage());
        }
        return result;
    }

    public int insertRole(String name, String description) {
        int result = 0;
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "INSERT INTO roles( name, description ) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            result = ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error insert role" + e.getMessage());
        }
        return result;
    }

    public int updateRole(RoleModel role) {
        int result = 0;
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "update roles set name = ?, description = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, role.getName());
            ps.setString(2, role.getDescription());
            ps.setInt(3, role.getId());
            result = ps.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error update role" + e.getMessage());
        }
        return result;
    }

}
