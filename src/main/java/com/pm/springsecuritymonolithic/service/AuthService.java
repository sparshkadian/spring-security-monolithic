package com.pm.springsecuritymonolithic.service;

import com.pm.springsecuritymonolithic.dto.AuthRequestDTO;
import com.pm.springsecuritymonolithic.model.Role;
import com.pm.springsecuritymonolithic.model.User;
import com.pm.springsecuritymonolithic.repository.UserRepository;
import com.pm.springsecuritymonolithic.security.CustomUserDetails;
import com.pm.springsecuritymonolithic.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public String login(AuthRequestDTO authRequestDto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getEmail(),
                        authRequestDto.getPassword()
                )
        );
        UserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return jwtUtil.generateToken(userDetails);
    }

    public String register(AuthRequestDTO authRequest) {
        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(authRequest.getPassword());
        user.setRole(Role.valueOf(authRequest.getRole()));

        User newUser = userRepository.save(user);
        UserDetails userDetails = new CustomUserDetails(newUser);
        return jwtUtil.generateToken(userDetails);
    }
}
