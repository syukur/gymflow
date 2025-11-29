package com.lylastudio.gymflow.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Gymflow API",
                version = "1.0",
                description = "Dokumentasi API untuk aplikasi manajemen gym"
        )
)
@Configuration
public class OpenApiConfig {}

