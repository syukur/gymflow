package com.lylastudio.gymflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MemberResponse {

    @Schema(description = "Member id")
    private String id;

    @Schema(description = "Member full name")
    private String fullName;

    @Schema(description = "Member phone number")
    private String phoneNumber;

    @Schema(description = "Member email")
    private String email;

    @Schema(description = "Membership type")
    private String membershipType;

    @Schema(description = "Member registered date")
    private LocalDate registeredAt;

    @Schema(description = "Member address")
    private String address;
}
