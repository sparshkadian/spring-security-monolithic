package com.pm.springsecuritymonolithic.controller;

import com.pm.springsecuritymonolithic.dto.AuthRequestDTO;
import com.pm.springsecuritymonolithic.dto.AuthResponseDTO;
import com.pm.springsecuritymonolithic.service.AuthService;
import com.pm.springsecuritymonolithic.validationGroups.OnLogin;
import jakarta.validation.groups.Default;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @Validated(Default.class) @RequestBody AuthRequestDTO authRequestDto
    ) {
        String token = authService.login(authRequestDto);
        log.info("User {} logged in successfully", authRequestDto.getEmail());
        return ResponseEntity.ok().body(new AuthResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @Validated({Default.class, OnLogin.class}) @RequestBody AuthRequestDTO authRequest
    ) {
        String token = authService.register(authRequest);
        log.info("User {} registered", authRequest.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(token));
    }
}
