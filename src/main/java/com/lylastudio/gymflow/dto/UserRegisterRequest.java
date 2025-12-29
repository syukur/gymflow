package com.lylastudio.gymflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    @Schema(description = "User name")
    @NotBlank(message = "{validation.username.required}")
    private String username;

    @Schema(description = "User Sure Name")
    @NotBlank(message = "{validation.sureName.required}")
    private String sureName;

    @Schema(description = "User password")
    @NotBlank(message = "{validation.password.required}")
    private String password;

    @Schema(description = "User Email")
    @NotBlank(message = "{validation.email.required}")
    private String email;

}
