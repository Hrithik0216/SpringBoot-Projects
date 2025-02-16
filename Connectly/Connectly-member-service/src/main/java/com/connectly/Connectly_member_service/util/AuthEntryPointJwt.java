package com.connectly.Connectly_member_service.util;

import java.io.IOException; // Import IOException for handling input/output exceptions
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException; // Import ServletException for servlet-related exceptions
import jakarta.servlet.http.HttpServletRequest; // Import HttpServletRequest for handling HTTP requests
import jakarta.servlet.http.HttpServletResponse; // Import HttpServletResponse for handling HTTP responses

import org.slf4j.Logger; // Import Logger for logging errors and information
import org.slf4j.LoggerFactory; // Import LoggerFactory for creating Logger instances
import org.springframework.security.core.AuthenticationException; // Import AuthenticationException for authentication errors
import org.springframework.security.web.AuthenticationEntryPoint; // Import AuthenticationEntryPoint for handling unauthorized access
import org.springframework.stereotype.Component; // Import Component for Spring component scanning

/**
 * Custom implementation of AuthenticationEntryPoint to handle unauthorized access.
 */
@Component // Indicate that this class is a Spring component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class); // Logger for logging errors

    /**
     * Handle unauthorized access attempts.
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param authException The exception that was thrown during authentication.
     * @throws IOException If an input or output exception occurs.
     * @throws ServletException If a servlet-related exception occurs.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // Log the unauthorized access attempt with the exception message
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> body = new HashMap<>();
        body.put("error", "Unauthorized");
        body.put("message", "Full authentication is required to access this resource");
        body.put("status", 401);
        body.put("path", request.getRequestURI());

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(body));
    }

}

