package com.lylastudio.gymflow.dto.onboarding;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Company Name")
    @NotBlank(message = "{validation.required}")
    @Size(min = 3, max = 50, message = "{validation.length}")
    private String companyName;

    @Schema(description = "Company mobile phone number, (recomendation: whatsapp number)")
    @NotBlank(message = "{validation.phone.required}")
    @Pattern(regexp = "^[0-9]+$", message = "{validation.phone.pattern}")
    @Size(min = 10, max = 20, message = "{validation.phone.length}")
    private String phoneNumber;

    @Schema(description = "Company Currency")
    @NotBlank(message = "{validation.required}")
    @Size(min = 3, max = 3, message = "{validation.length}")
    private String currency;

    @Schema(description = "List of package")
    @Valid
    private List<Package> packages;

    @Schema(description = "List of branch")
    @Valid
    private List<Branch> branches;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Package {

        @Schema(description = "Package Name")
        @NotBlank(message = "{validation.required}")
        @Size(min = 3, max = 50, message = "{validation.length}")
        private String name;

        @Schema(description = "Package Price")
        @NotNull(message = "{validation.required}")
        @PositiveOrZero(message = "{validation.must.number}")
        private Double price;

        @Schema(description = "Package Duration")
        @NotNull(message = "{validation.required}")
        @PositiveOrZero(message = "{validation.must.number}")
        private Integer duration; // Duration in days or months, depending on your logic
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Branch {

        @Schema(description = "Branch Name")
        @NotBlank(message = "{validation.required}")
        @Size(min = 3, max = 50, message = "{validation.length}")
        private String name;

        @Schema(description = "Branch Address")
        @NotBlank(message = "{validation.required}")
        @Size(min = 3, max = 50, message = "{validation.length}")
        private String address;
    }
}
