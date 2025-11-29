package com.lylastudio.gymflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class CompanyRequest {

    @Schema(description = "Company Name")
    @NotBlank(message = "{validation.companyName.required}")
    @Size(min = 2, max = 200, message = "{validation.companyName.length}")
    private String companyName;

    @Schema(description = "Company Address")
    @Size(max = 500, message = "{validation.company.address.length}")
    private String address;
}

