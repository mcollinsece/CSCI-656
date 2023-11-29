package com.voteapi.login;

public class Application {

    public static void main(String[] args) {
        AuthenticationService authService = new AuthenticationService();
        AuthorizationService authzService = new AuthorizationService();

        String username = "testUser";
        String password = "hashedPasswordHere"; // In real applications, get it securely

        try {
            String token = authService.authenticate(username, password);
            if (token != null) {
                System.out.println("Authentication successful! Token: " + token);

                // Example of checking authorization
                if (authzService.isAuthorized(token, "admin")) {
                    System.out.println("User is authorized as admin.");
                } else {
                    System.out.println("User is not authorized as admin.");
                }
            } else {
                System.out.println("Authentication failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
