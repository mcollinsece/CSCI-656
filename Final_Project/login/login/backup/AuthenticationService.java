package com.voteapi.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@Transactional
public class AuthenticationService {

    // Consider using Spring's @Autowired to inject a DataSource or JdbcTemplate
    // for better integration with Spring's data handling

    public String authenticate(String username, String password) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                System.out.println(username);
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String storedPassword = rs.getString("password");
                        // IMPORTANT: Replace this password comparison with a secure method
                        // like BCrypt's checkpw method if you're hashing passwords
                        if (password.equals(storedPassword)) {
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


// Recommended Changes for Spring Boot Integration:
// Use Spring Data JPA: Instead of manually handling Connection, PreparedStatement, and ResultSet, use Spring Data JPA repositories for database operations. This will simplify your code and leverage Spring's transaction management.

// Secure Password Handling: If you are storing hashed passwords in your database (which is recommended), use a password encoder like BCryptPasswordEncoder for password comparison.

// Environment Variables for Secrets: Avoid hardcoding secrets like JWT keys in your code. Use environment variables or Spring's @Value annotation to externalize such configurations.

// Exception Handling: Consider more robust exception handling and logging. This is especially important in a service handling authentication.

// Service Layer Responsibilities: Ensure that your service layer is focused on business logic. Authentication logic can sometimes be better handled in a separate security configuration class, especially when using Spring Security.