package com.lylastudio.gymflow.service;

import com.lylastudio.gymflow.dto.CompanyRequest;
import com.lylastudio.gymflow.dto.CompanyResponse;
import org.springframework.data.domain.Page;

public interface CompanyService {
    CompanyResponse createCompany(CompanyRequest request);
    CompanyResponse getCompanyById(String id);
    Page<CompanyResponse> getCompanies(int page, int size);
    CompanyResponse updateCompany(String id, CompanyRequest request);
    void deleteCompany(String id);
}

