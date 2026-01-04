package com.lylastudio.gymflow.service.impl;

import com.lylastudio.gymflow.dto.*;
import com.lylastudio.gymflow.entity.MUser;
import com.lylastudio.gymflow.repository.MUserRepository;
import com.lylastudio.gymflow.security.AppUser;
import com.lylastudio.gymflow.security.JwtUtil;
import com.lylastudio.gymflow.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public void register(UserRegisterRequest userRegisterRequest) {
        MUser user = new MUser();
        user.setUsername(userRegisterRequest.getUsername());
        user.setSureName(userRegisterRequest.getSureName());
        user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        user.setEmail(userRegisterRequest.getEmail());
        user.setAuthProvider("EMAIL");
        userRepository.save(user);
    }

    @Override
    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        MUser user = userService.loadRawUserByUsername(authRequest.getUsername());
        UserDetails userDetails = new AppUser(user);
        String jwt = jwtUtil.generateToken(userDetails, user.getCompany().getId());
        String refreshToken = jwtUtil.generateRefreshToken(userDetails, user.getCompany().getId());

        //MUser user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return new AuthResponse(jwt, refreshToken);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse refreshToken(String refreshToken) {
        String username = jwtUtil.extractUsername(refreshToken);
        MUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!user.getRefreshToken().equals(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        //UserDetails userDetails = userService.loadUserByUsername(username);
        UserDetails userDetails = new AppUser(user);

        String newJwt = jwtUtil.generateToken(userDetails, user.getCompany().getId());

        return new AuthResponse(newJwt, refreshToken);
    }

}
