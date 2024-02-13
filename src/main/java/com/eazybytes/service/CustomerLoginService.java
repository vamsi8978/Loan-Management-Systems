package com.eazybytes.service;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

@Service
public class CustomerLoginService {

    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/eazybank";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public void logCustomerLogin(int customerId) {
        String sql = "INSERT INTO customer_audit (customer_id, last_login) VALUES (?, ?)";

        try (
                Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, customerId);
            stmt.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL exceptions
        }
    }
}
