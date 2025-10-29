package com.lylastudio.gymflow.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MemberResponse {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String membershipType;
    private LocalDate registeredAt;
    private String address;
}
