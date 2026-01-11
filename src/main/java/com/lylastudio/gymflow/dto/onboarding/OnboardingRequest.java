package com.lylastudio.gymflow.dto.onboarding;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OnboardingRequest {

    private String companyName;
    private String phoneNumber;
    private String currency;
    private List<Package> packages;
    private List<Branch> branches;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Package {
        private String name;
        private Double price;
        private Integer duration; // Duration in days or months, depending on your logic
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Branch {
        private String name;
        private String address;
    }
}
