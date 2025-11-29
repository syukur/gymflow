package com.lylastudio.gymflow.controller;

import com.lylastudio.gymflow.dto.ApiResponse;
import com.lylastudio.gymflow.dto.AuthRequest;
import com.lylastudio.gymflow.dto.AuthResponse;
import com.lylastudio.gymflow.dto.RefreshTokenRequest;
import com.lylastudio.gymflow.service.AuthService;
import com.lylastudio.gymflow.util.ApiResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ApiResponseUtil responseUtil;

    @Operation(summary = "User register endpoint", description = "For register new user")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@RequestBody AuthRequest authRequest) {
        authService.register(authRequest);
        return ResponseEntity.ok(responseUtil.success("user.register.success"));
    }

    @Operation(summary = "Login endpoint", description = "For login user")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest authRequest) {
        AuthResponse response = authService.login(authRequest);
        return ResponseEntity.ok(responseUtil.success("auth.login.success", response));
    }

    @Operation(summary = "Refresh token endpoint", description = "For refresh new token, if access token is expired")
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        AuthResponse response = authService.refreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok(responseUtil.success("auth.refresh.success", response));
    }

}
