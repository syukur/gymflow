package com.lylastudio.gymflow.service.impl;

import com.lylastudio.gymflow.dto.CompanyResponse;
import com.lylastudio.gymflow.dto.MemberResponse;
import com.lylastudio.gymflow.dto.onboarding.OnboardingRequest;
import com.lylastudio.gymflow.dto.onboarding.OnboardingResponse;
import com.lylastudio.gymflow.entity.*;
import com.lylastudio.gymflow.repository.MBranchRepository;
import com.lylastudio.gymflow.repository.MCompanyRepository;
import com.lylastudio.gymflow.repository.MPackageRespository;
import com.lylastudio.gymflow.repository.MUserRepository;
import com.lylastudio.gymflow.service.OnboardingService;
import com.lylastudio.gymflow.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OnboardingServiceImpl implements OnboardingService {

    private final MCompanyRepository companyRepository;
    private final MBranchRepository branchRepository;
    private final MPackageRespository packageRespository;
    private final MUserRepository userRepository;

    @Override
    @Transactional
    public OnboardingResponse onboard(OnboardingRequest request) {

        // 1. Register Company
        MCompany company = MCompany.builder()
                .name(request.getCompanyName())
                .phoneNumber(request.getPhoneNumber())
                .currency(request.getCurrency())
                .build();

        // 2. Register Package
        List<MPackage> packages =
                request.getPackages()
                       .stream()
                       .map(requestPackage ->
                               MPackage.builder()
                                       .name( requestPackage.getName() )
                                       .price( requestPackage.getPrice() )
                                       .duration( requestPackage.getDuration() )
                                       .gymAccess(true)
                                       .company(company)
                                       .build()
                        )
                        .toList();

        company.setPackages(packages);


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

        company.setBranches(branches);

        // 4. Update companyId for current user
        UserDetails userDetails = SecurityUtils.getCurrentUser();

        MUser user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("user.not.found"));

        user.setCompany(company);

        companyRepository.save(company);

        // 5. Return Response

        return mapToResponse(company);

    }

    private OnboardingResponse mapToResponse(MCompany company) {
        //return OnboardingResponse.builder().build();


//        List<OnboardingResponse.Package> packages = null;
//        if (company.getPackages() != null) {
//            packages = company.getPackages()
//                            .stream()
//                            .map(this::mapPackage)
//                            .toList();
//        }

        List<OnboardingResponse.Package> packages =
                Optional.ofNullable(company.getPackages())
                        .orElse(List.of())
                        .stream()
                        .map(this::mapPackage)
                        .toList();

        List<OnboardingResponse.Branch> branches =
                Optional.ofNullable(company.getBranches())
                        .orElse(List.of())
                        .stream()
                        .map(this::mapBranch)
                        .toList();

        return OnboardingResponse.builder()
                .companyId(company.getId())
                .companyName(company.getName())
                .phoneNumber(company.getPhoneNumber())
                .currency(company.getCurrency())
                .packages(packages)
                .branches(branches)
                .build();
    }

    private OnboardingResponse.Package mapPackage( MPackage b) {
        return OnboardingResponse.Package.builder()
                .id(b.getId())
                .name(b.getName())
                .price(b.getPrice())
                .duration(b.getDuration())
                .build();
    }

    private OnboardingResponse.Branch mapBranch( MBranch b) {
        return OnboardingResponse.Branch.builder()
                .id(b.getId())
                .name(b.getName())
                .address(b.getAddress())
                .build();
    }

}
