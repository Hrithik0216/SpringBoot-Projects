package com.connectly.Connectly_member_service.controller;

import com.connectly.Connectly_member_service.Repository.UserRepository;
import com.connectly.Connectly_member_service.dto.AuthRequest;
import com.connectly.Connectly_member_service.dto.AuthResponse;
import com.connectly.Connectly_member_service.model.User;
import com.connectly.Connectly_member_service.service.CustomUserDetailsService;
import com.connectly.Connectly_member_service.util.AuthEntryPointJwt;
import com.connectly.Connectly_member_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signIn")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println("Attempting authentication for: " + authRequest.getEmail());

            // Verify user exists
            User user = userRepository.findByEmail(authRequest.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + authRequest.getEmail()));
            System.out.println("Stored encoded password: " + user.getPassword());

            // Verify password matches
            if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                System.out.println("Password does not match!");
                return ResponseEntity.status(403).body("Invalid email or password.");
            }

            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            // Load user details and generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
            String jwt = jwtUtil.generateToken(userDetails);
//                 System.out.println("JWT Token generated: " + jwt);
            AuthResponse authResponse = new AuthResponse();
            authResponse.setJwt(jwt);
            return ResponseEntity.ok(authResponse);

        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed: Invalid email or password.");
            return ResponseEntity.status(403).body("Invalid email or password.");
        } catch (Exception e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(500).body("Authentication failed: " + e.getMessage());
        }
    }

    @GetMapping("/dummy")
    public ResponseEntity<String> dummyCheckpoint(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hey Hrithik");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
