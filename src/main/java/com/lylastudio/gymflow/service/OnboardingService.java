package com.lylastudio.gymflow.service;

import com.lylastudio.gymflow.dto.onboarding.OnboardingRequest;
import com.lylastudio.gymflow.dto.onboarding.OnboardingResponse;

public interface OnboardingService {
    OnboardingResponse onboard(OnboardingRequest request);
}
