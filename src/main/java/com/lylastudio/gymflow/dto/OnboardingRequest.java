package com.lylastudio.gymflow.dto;

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
    private String whatsapp;
    private String currency;
    private List<Membership> memberships;
    private List<Branch> branches;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Membership {
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
