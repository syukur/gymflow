package com.lylastudio.gymflow.service;

import com.lylastudio.gymflow.dto.AuthRequest;
import com.lylastudio.gymflow.dto.AuthResponse;
import com.lylastudio.gymflow.dto.UserRegisterRequest;

public interface AuthService {
    void register(UserRegisterRequest request);
    AuthResponse login(AuthRequest request);
    AuthResponse refreshToken(String refreshToken);
}
