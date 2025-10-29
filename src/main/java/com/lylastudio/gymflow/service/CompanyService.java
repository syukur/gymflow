package com.lylastudio.gymflow.service;

import com.lylastudio.gymflow.dto.CompanyRequest;
import com.lylastudio.gymflow.dto.CompanyResponse;
import org.springframework.data.domain.Page;

public interface CompanyService {
    CompanyResponse createCompany(CompanyRequest request);
    CompanyResponse getCompanyById(Long id);
    Page<CompanyResponse> getCompanies(int page, int size);
    CompanyResponse updateCompany(Long id, CompanyRequest request);
    void deleteCompany(Long id);
}

