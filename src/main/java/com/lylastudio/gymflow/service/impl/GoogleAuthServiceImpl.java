package com.lylastudio.gymflow.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.lylastudio.gymflow.dto.GoogleAuthRequest;
import com.lylastudio.gymflow.dto.GoogleAuthResponse;
import com.lylastudio.gymflow.entity.MEnpoint;
import com.lylastudio.gymflow.entity.MRole;
import com.lylastudio.gymflow.entity.MUser;
import com.lylastudio.gymflow.entity.TRoleEnpoint;
import com.lylastudio.gymflow.repository.MEnpointRespository;
import com.lylastudio.gymflow.repository.MRoleRepository;
import com.lylastudio.gymflow.repository.MUserRepository;
import com.lylastudio.gymflow.security.AppUser;
import com.lylastudio.gymflow.security.JwtUtil;
import com.lylastudio.gymflow.service.GoogleAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GoogleAuthServiceImpl implements GoogleAuthService {

    private final GoogleIdTokenVerifier verifier;
    private final JwtUtil jwtUtil;
    private final String CLIENT_ID = "422189884154-o53l490aqm132t5542ki4mli0i61oe8m.apps.googleusercontent.com";
    private final MRoleRepository roleRepository;
    private final MEnpointRespository enpointRespository;
    private final MUserRepository userRepository;

    public GoogleAuthServiceImpl(MRoleRepository roleRepository, MEnpointRespository enpointRespository, MUserRepository userRepository, JwtUtil jwtUtil) {

        this.roleRepository = roleRepository;
        this.enpointRespository = enpointRespository;
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

    @Override
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


        MUser user = userRepository.findByEmail(email).orElseGet(() -> {

            MRole role = roleRepository.findByName("OWNER").orElseThrow(() ->  new BadCredentialsException("auth.bad.credentials"));

            MUser usr = new MUser();
            usr.setUsername(email);
            usr.setSureName(name);
            usr.setPassword("");
            usr.setSub(sub);
            usr.setAuthProvider("GOOGLE");
            usr.setEmail(email);
            usr.setRole(role);
            userRepository.save(usr);
            return usr;
        });


        AppUser appUser = new AppUser(user);

        // 2. Buat JWT token
        String accessToken;
        String refreshToken;
        try {
            accessToken  = jwtUtil.generateToken(appUser, user.getCompany().getId());
            refreshToken = jwtUtil.generateRefreshToken(appUser, user.getCompany().getId());
        }catch (NullPointerException npe){
            accessToken  = jwtUtil.generateToken(appUser, null);
            refreshToken = jwtUtil.generateRefreshToken(appUser, null);
        }


        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        // 3. Response
        return new GoogleAuthResponse(accessToken, refreshToken, email, name);
    }


}
