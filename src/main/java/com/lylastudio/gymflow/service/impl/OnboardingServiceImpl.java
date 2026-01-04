package com.lylastudio.gymflow.service.impl;

import com.lylastudio.gymflow.dto.onboarding.OnboardingRequest;
import com.lylastudio.gymflow.dto.onboarding.OnboardingResponse;
import com.lylastudio.gymflow.entity.MBranch;
import com.lylastudio.gymflow.entity.MCompany;
import com.lylastudio.gymflow.entity.MPackage;
import com.lylastudio.gymflow.repository.MBranchRepository;
import com.lylastudio.gymflow.repository.MCompanyRepository;
import com.lylastudio.gymflow.repository.MPackageRespository;
import com.lylastudio.gymflow.service.OnboardingService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OnboardingServiceImpl implements OnboardingService {

    private final MCompanyRepository companyRepository;
    private final MBranchRepository branchRepository;
    private final MPackageRespository packageRespository;

    @Override
    public OnboardingResponse onboard(OnboardingRequest request) {

        // 1. Register Company
        MCompany company = MCompany.builder()
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .currency(request.getCurrency())
                .build();

        companyRepository.save(company);

        // 2. Register Package
        List<MPackage> packages =
                request.getPackages()
                        .stream()
                        .map(requestPackage ->
                            MPackage.builder()
                                    .name( requestPackage.getName() )
                                    .price( requestPackage.getDuration() )
                                    .duration( requestPackage.getDuration() )
                                    .gymAccess(true)
                                    .build()
                        ).toList();
        packageRespository.saveAll(packages);

        // 3. Register Branch
        List<MBranch> branches =
                request.getBranches()
                        .stream()
                        .map(requestBranch ->
                            MBranch.builder()
                                    .name(requestBranch.getName())
                                    .address(requestBranch.getAddress())
                                    .company(company)
                                    .build()
                        )
                        .toList();

        branchRepository.saveAll(branches);

        return mapToResponse(company);

    }

    private OnboardingResponse mapToResponse(MCompany company) {
        return OnboardingResponse.builder().build();
    }

}
