package com.voteapi.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            if (token != null) {
                return ResponseEntity.ok(new AuthResponse(token));
            } else {
                return ResponseEntity.status(401).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred");
        }
    }
}
