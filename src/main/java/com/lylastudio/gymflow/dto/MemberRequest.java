package com.lylastudio.gymflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

@Data
public class MemberRequest {

    @Schema(description = "Member full name")
    @NotBlank(message = "{validation.fullName.required}")
    @Size(min = 5, max = 100, message = "{validation.fullName.length}")
    private String fullName;

    @Schema(description = "Member phone number")
    @NotBlank(message = "{validation.phone.required}")
    @Pattern(regexp = "^[0-9]+$", message = "{validation.phone.pattern}")
    @Size(min = 10, max = 20, message = "{validation.phone.length}")
    private String phoneNumber;

    @Schema(description = "Member email")
    @NotBlank(message = "{validation.email.required}")
    @Email(message = "{validation.email.pattern}")
    private String email;

    @Schema(description = "Member date of birth")
    @NotNull(message = "{validation.dateOfBirth.required}")
    @Past(message = "{validation.dateOfBirth.past}")
    private LocalDate dateOfBirth;

    @Schema(description = "Membership Type")
    @NotBlank(message = "{validation.membershipType.required=}")
    @Pattern(regexp = "^(BASIC|PREMIUM|VIP)$", message = "{validation.membershipType.invalid}")
    private String membershipType;

    @Schema(description = "Member Address")
    @NotBlank(message = "{validation.address.required}")
    @Size(min = 10, max = 255, message = "validation.address.length")
    private String address;
}
