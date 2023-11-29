package com.voteapi.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class AuthorizationService {

    public boolean isAuthorized(String token, String requiredRole) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256("your_secret_key"))
                                .build()
                                .verify(token);
            String role = jwt.getClaim("role").asString();
            return requiredRole.equals(role);
        } catch (Exception e) {
            return false;
        }
    }
}
