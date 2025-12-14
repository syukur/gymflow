package com.lylastudio.gymflow.service;

import com.lylastudio.gymflow.dto.GoogleAuthRequest;
import com.lylastudio.gymflow.dto.GoogleAuthResponse;

public interface GoogleAuthService {
    GoogleAuthResponse login(GoogleAuthRequest googleAuthRequest);
}
