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
public class OnboardingResponse {

    private String companyId;
    private String companyName;
    private String phoneNumber;
    private String currency;
    private List<Package> packages;
    private List<Branch> branches;

    private String newAccessToken;
    private String newRefreshToken;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Package {
       private String id;
        private String name;
        private Double price;
        private Integer duration; // Duration in days or months, depending on your logic
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Branch {
        private String id;
        private String name;
        private String address;
    }
}
