package com.lylastudio.gymflow.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyResponse {
    private Long id;
    private String companyName;
    private String address;
    private List<MemberResponse> members;
}

