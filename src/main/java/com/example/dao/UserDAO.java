package com.example.dao;

import com.example.model.User;
import com.example.utils.DatabaseConfig;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDAO {

    public void saveUser(User user) throws SQLException {
        String sql = "INSERT INTO users (email, password, secret_key) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getEmail());
            // Hash the password before storing
            pstmt.setString(2, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            pstmt.setString(3, user.getSecretKey());

            pstmt.executeUpdate();
        }
    }

    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("secret_key")
                    );
                }
            }
        }
        return null;
    }

    public boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}