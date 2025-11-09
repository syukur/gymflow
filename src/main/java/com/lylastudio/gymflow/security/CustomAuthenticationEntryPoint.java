package com.lylastudio.gymflow.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lylastudio.gymflow.dto.ApiResponse;
import com.lylastudio.gymflow.util.ApiResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ApiResponseUtil responseUtil;
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String messageKey = "auth.jwt.signature.invalid"; // Default message

        // Check the root cause for more specific JWT errors
        Throwable cause = authException.getCause();
        if (cause instanceof SignatureException) {
            messageKey = "jwt.signature.invalid";
        } else if (cause instanceof ExpiredJwtException) {
            messageKey = "jwt.token.expired";
        } else if (cause instanceof MalformedJwtException) {
            messageKey = "jwt.token.malformed";
        }

        ApiResponse<?> apiResponse = responseUtil.error(messageKey);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
