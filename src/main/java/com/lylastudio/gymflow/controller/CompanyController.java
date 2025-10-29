package com.lylastudio.gymflow.controller;

import com.lylastudio.gymflow.dto.ApiResponse;
import com.lylastudio.gymflow.dto.CompanyRequest;
import com.lylastudio.gymflow.dto.CompanyResponse;
import com.lylastudio.gymflow.service.CompanyService;
import com.lylastudio.gymflow.util.ApiResponseUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final ApiResponseUtil responseUtil;

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyResponse>> createCompany(@Valid @RequestBody CompanyRequest request) {
        CompanyResponse response = companyService.createCompany(request);
        return ResponseEntity.ok(responseUtil.success("company.create.success", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CompanyResponse>>> listCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CompanyResponse> companies = companyService.getCompanies(page, size);
        return ResponseEntity.ok(responseUtil.success("company.list.success", companies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> getCompany(@PathVariable Long id) {
        CompanyResponse company = companyService.getCompanyById(id);
        return ResponseEntity.ok(responseUtil.success("company.get.success", company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyResponse>> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyRequest request) {
        CompanyResponse updated = companyService.updateCompany(id, request);
        return ResponseEntity.ok(responseUtil.success("company.update.success", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok(responseUtil.success("company.delete.success"));
    }
}

