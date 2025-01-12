package com.flipkartProduct.product.utils;

import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public final class JwtUtils {
    public static String validateJwtTokenAndGetUserId(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            // Verify the expiration
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                throw new RuntimeException("Token is expired");
            }
            // Extract subject for further validation
            String subject = signedJWT.getJWTClaimsSet().getSubject();
            return subject;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean validateJwtTokenAndGetRoles(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            // Verify the expiration
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
            if (new Date().after(expirationTime)) {
                throw new RuntimeException("Token is expired");
            }
            // Extract roles and subject for further validation
            String subject = signedJWT.getJWTClaimsSet().getSubject();
            Object roles = signedJWT.getJWTClaimsSet().getClaim("roles");
            if (roles instanceof List) {
                List<String> rolesList = (List<String>) roles;
                boolean result = rolesList.stream().anyMatch(e -> e.equalsIgnoreCase("role_user"));
                return result;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
