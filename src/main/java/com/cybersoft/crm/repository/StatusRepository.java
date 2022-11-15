package com.cybersoft.crm.repository;

import com.cybersoft.crm.config.MysqlConnection;
import com.cybersoft.crm.entity.StatusEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {

    public List<StatusEntity> getStatuses() {
        List<StatusEntity> statuses = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getConnection();
            String query = "select * from status";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                StatusEntity status = new StatusEntity();
                status.setId(rs.getInt("id"));
                status.setName(rs.getString("name"));
                statuses.add(status);
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println("Error get statuses " + e.getMessage());
        }
        return statuses;
    }
}
