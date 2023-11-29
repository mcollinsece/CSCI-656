package com.voteapi.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationService {

    public String authenticate(String username, String password) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("password");
                        if (password.equals(storedPassword)) { // Use a secure password comparison
                            String role = rs.getString("role");

                            return JWT.create()
                                      .withSubject(username)
                                      .withClaim("role", role)
                                      .sign(Algorithm.HMAC256("your_secret_key"));
                        }
                    }
                }
            }
        }
        return null;
    }
}
