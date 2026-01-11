package com.lylastudio.gymflow.dto.onboarding;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "{validation.required}")
    @Size(min = 3, max = 50, message = "{validation.length}")
    private String companyName;

    @NotBlank(message = "{validation.phone.required}")
    @Pattern(regexp = "^[0-9]+$", message = "{validation.phone.pattern}")
    @Size(min = 10, max = 20, message = "{validation.phone.length}")
    private String phoneNumber;

    @NotBlank(message = "{validation.required}")
    @Size(min = 3, max = 3, message = "{validation.length}")
    private String currency;

    @Valid
    private List<Package> packages;

    @Valid
    private List<Branch> branches;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Package {

        @NotBlank(message = "{validation.required}")
        @Size(min = 3, max = 50, message = "{validation.length}")
        private String name;

        @NotNull(message = "{validation.required}")
        @PositiveOrZero(message = "{validation.must.number}")
        private Double price;

        @NotNull(message = "{validation.required}")
        @PositiveOrZero(message = "{validation.must.number}")
        private Integer duration; // Duration in days or months, depending on your logic
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Branch {

        @NotBlank(message = "{validation.required}")
        @Size(min = 3, max = 50, message = "{validation.length}")
        private String name;

        @NotBlank(message = "{validation.required}")
        @Size(min = 3, max = 50, message = "{validation.length}")
        private String address;
    }
}
