package com.connectly.Connectly_member_service.service;

import com.connectly.Connectly_member_service.Repository.UserRepository;
import com.connectly.Connectly_member_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

//        System.out.println("User found: " + user.getEmail());
//        System.out.println("Stored encoded password: " + user.getPassword());

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : user.getRoles()) {
            String formattedRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;
            authorities.add(new SimpleGrantedAuthority(formattedRole));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );

        // ✅ Add debug logs to verify what's being returned
        System.out.println("Returning UserDetails:");
        System.out.println("Username: " + userDetails.getUsername());
        System.out.println("Password: " + userDetails.getPassword());
        System.out.println("Authorities: " + userDetails.getAuthorities());

        return userDetails;
    }


}
