package com.lylastudio.gymflow.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.lylastudio.gymflow.dto.GoogleAuthRequest;
import com.lylastudio.gymflow.dto.GoogleAuthResponse;
import com.lylastudio.gymflow.entity.MUser;
import com.lylastudio.gymflow.repository.MUserRepository;
import com.lylastudio.gymflow.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
public class GoogleAuthService {

    private final GoogleIdTokenVerifier verifier;
    private final UserService userService;
    private final MUserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final String CLIENT_ID = "422189884154-o53l490aqm132t5542ki4mli0i61oe8m.apps.googleusercontent.com";

    public GoogleAuthService(UserService userService, MUserRepository userRepository, JwtUtil jwtUtil) {

        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;

        this.verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                new GsonFactory()
        ).setAudience(Collections.singletonList(CLIENT_ID))
                .build();

    }

    private GoogleIdToken.Payload verify(String idTokenString) throws Exception {
        log.info("idTokenString: {}", idTokenString);
        GoogleIdToken idToken = verifier.verify(idTokenString);

        if (idToken == null) {
            throw new RuntimeException("Invalid Google ID Token");
        }

        return idToken.getPayload();
    }


    @Transactional
    public GoogleAuthResponse login(GoogleAuthRequest googleAuthRequest) {

        String idToken = googleAuthRequest.getIdToken();

        // 1. Verifikasi token
        GoogleIdToken.Payload payload = null;
        try {
            payload = verify(idToken);

        } catch (Exception e) {
            log.error("Error verifying Google ID Token: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }

        String email = payload.getEmail();
        String name  = (String) payload.get("name");
        String sub   = payload.getSubject(); // google unique ID

        log.info("payload: {},  email: {}, name: {}, sub: {}",payload, email,name,sub);

        UserDetails userDetails= userService.loadByEmail(email).orElseGet(()->
                userService.registerGoogleUser(email, name, sub)
        );

        // 2. Buat JWT token
        String accessToken  = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        MUser user = userRepository.findByEmail(email).orElseThrow();

        user.setRefreshToken(refreshToken);
        userRepository.save(user);


        // 3. Response
        return new GoogleAuthResponse(accessToken, refreshToken, email, name);
    }


}
