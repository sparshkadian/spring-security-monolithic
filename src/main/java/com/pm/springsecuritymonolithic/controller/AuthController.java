package com.pm.springsecuritymonolithic.controller;

import com.pm.springsecuritymonolithic.dto.AuthRequestDTO;
import com.pm.springsecuritymonolithic.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody AuthRequestDTO authRequest
    ) {
        String token = authService.register(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", token));
    }
}
