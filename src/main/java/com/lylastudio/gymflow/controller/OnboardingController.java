package com.lylastudio.gymflow.controller;

import com.lylastudio.gymflow.dto.*;
import com.lylastudio.gymflow.dto.onboarding.OnboardingRequest;
import com.lylastudio.gymflow.dto.onboarding.OnboardingResponse;
import com.lylastudio.gymflow.service.OnboardingService;
import com.lylastudio.gymflow.service.impl.AuthServiceImpl;
import com.lylastudio.gymflow.service.impl.GoogleAuthServiceImpl;
import com.lylastudio.gymflow.service.impl.OnboardingServiceImpl;
import com.lylastudio.gymflow.util.ApiResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/onboarding")
@RequiredArgsConstructor
@Slf4j
public class OnboardingController {


    private final OnboardingService onboardingService;
    private final ApiResponseUtil responseUtil;

    @Operation(summary = "Onboarding endpoint", description = "Onboarding endpoint")
    @PostMapping
    public ResponseEntity<ApiResponse<OnboardingResponse>> register(@Valid @RequestBody OnboardingRequest onboardingRequest) {
        log.info("Onboarding request: {}", onboardingRequest);
        OnboardingResponse response = onboardingService.onboard(onboardingRequest);
        return ResponseEntity.ok(responseUtil.success("success", response));
    }
}
