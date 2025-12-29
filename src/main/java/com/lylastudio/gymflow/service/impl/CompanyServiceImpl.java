package com.lylastudio.gymflow.service.impl;

import com.lylastudio.gymflow.dto.CompanyRequest;
import com.lylastudio.gymflow.dto.CompanyResponse;
import com.lylastudio.gymflow.dto.MemberResponse;
import com.lylastudio.gymflow.entity.MCompany;
import com.lylastudio.gymflow.entity.MMember;
import com.lylastudio.gymflow.repository.MCompanyRepository;
import com.lylastudio.gymflow.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final MCompanyRepository companyRepository;

    @Override
    public CompanyResponse createCompany(CompanyRequest request) {
        MCompany company = MCompany.builder()
                .name(request.getCompanyName())
                .address(request.getAddress())
                .build();

        companyRepository.save(company);
        return mapToResponse(company);
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        MCompany company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("company.not.found"));
        return mapToResponse(company);
    }

    @Override
    public Page<CompanyResponse> getCompanies(int page, int size) {
        var pageReq = PageRequest.of(Math.max(0, page), Math.max(1, size));
        var companiesPage = companyRepository.findAll(pageReq);
        List<CompanyResponse> content = companiesPage.getContent().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(content, pageReq, companiesPage.getTotalElements());
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest request) {
        MCompany company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("company.not.found"));

        company.setName(request.getCompanyName());
        company.setAddress(request.getAddress());

        companyRepository.save(company);
        return mapToResponse(company);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    private CompanyResponse mapToResponse(MCompany company) {
        List<MemberResponse> members = null;
        if (company.getMembers() != null) {
            members = company.getMembers().stream()
                    .map(this::mapMember)
                    .collect(Collectors.toList());
        }

        return CompanyResponse.builder()
                .id(company.getId())
                .companyName(company.getName())
                .address(company.getAddress())
                .members(members)
                .build();
    }

    private MemberResponse mapMember(MMember m) {
        return MemberResponse.builder()
                .id(m.getId())
                .fullName(m.getFullName())
                .email(m.getEmail())
                .phoneNumber(m.getPhoneNumber())
                .membershipType(m.getMembershipType())
                .registeredAt(m.getRegisteredAt())
                .address(m.getAddress())
                .build();
    }
}
