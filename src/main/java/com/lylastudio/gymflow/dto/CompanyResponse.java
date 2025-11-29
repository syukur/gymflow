package com.lylastudio.gymflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CompanyResponse {

    @Schema(description = "Company Id")
    private Long id;

    @Schema(description = "Company Name")
    private String companyName;

    @Schema(description = "Company Descryption")
    private String address;

    @Schema(description = "Company Members")
    private List<MemberResponse> members;
}

