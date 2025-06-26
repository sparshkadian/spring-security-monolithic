package com.pm.springsecuritymonolithic.controller;

import com.pm.springsecuritymonolithic.dto.AuthRequestDTO;
import com.pm.springsecuritymonolithic.dto.AuthResponseDTO;
import com.pm.springsecuritymonolithic.service.AuthService;
import com.pm.springsecuritymonolithic.validationGroups.OnLogin;
import jakarta.validation.groups.Default;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @Validated(Default.class) @RequestBody AuthRequestDTO authRequestDto
    ) {
        String token = authService.login(authRequestDto);
        return ResponseEntity.ok().body(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Validated({Default.class, OnLogin.class}) @RequestBody AuthRequestDTO authRequest
    ) {
        String token = authService.register(authRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", token));
    }
}
