package com.lylastudio.gymflow.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class CompanyRequest {

    @NotBlank(message = "{validation.companyName.required}")
    @Size(min = 2, max = 200, message = "{validation.companyName.length}")
    private String companyName;

    @Size(max = 500, message = "{validation.company.address.length}")
    private String address;
}

