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
public class GoogleAuthResponse {

    @Schema(description = "Google Access token")
    private String jwt;

    @Schema(description = "Google Refresh token")
    private String refreshToken;

    @Schema(description = "Google User Email")
    private String email;

    @Schema(description = "Google User Name")
    private String name;
}
