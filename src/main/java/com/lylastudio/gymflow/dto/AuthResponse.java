package com.lylastudio.gymflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    @Schema(description = "Access token")
    private String jwt;

    @Schema(description = "Refresh token")
    private String refreshToken;
}
