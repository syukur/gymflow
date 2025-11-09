package com.lylastudio.gymflow.service;

import com.lylastudio.gymflow.dto.AuthRequest;
import com.lylastudio.gymflow.dto.AuthResponse;
import com.lylastudio.gymflow.entity.MUser;
import com.lylastudio.gymflow.repository.MUserRepository;
import com.lylastudio.gymflow.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Transactional
    public void register(AuthRequest authRequest) {
        MUser user = new MUser();
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        MUser user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return new AuthResponse(jwt, refreshToken);
    }

    @Transactional(readOnly = true)
    public AuthResponse refreshToken(String refreshToken) {
        String username = jwtUtil.extractUsername(refreshToken);
        MUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!user.getRefreshToken().equals(refreshToken)) {
            throw new RuntimeException("Invalid refresh token");
        }

        UserDetails userDetails = userService.loadUserByUsername(username);
        String newJwt = jwtUtil.generateToken(userDetails);

        return new AuthResponse(newJwt, refreshToken);
    }
}
