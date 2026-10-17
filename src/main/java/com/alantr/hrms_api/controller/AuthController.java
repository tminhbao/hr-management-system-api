package com.alantr.hrms_api.controller;

import com.alantr.hrms_api.model.dto.request.LoginRequest;
import com.alantr.hrms_api.model.dto.response.LoginResponse;
import com.alantr.hrms_api.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final long jwtExpiration;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            @Value("${jwt.expiration}") long jwtExpiration) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.jwtExpiration = jwtExpiration;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        String token = this.jwtService.generateToken(authentication);

        return new LoginResponse(token, "Bearer", this.jwtExpiration);
    }
}

