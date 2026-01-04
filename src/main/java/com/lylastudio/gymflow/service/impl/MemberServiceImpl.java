package com.lylastudio.gymflow.service.impl;

import com.lylastudio.gymflow.dto.MemberRequest;
import com.lylastudio.gymflow.dto.MemberResponse;
import com.lylastudio.gymflow.entity.MCompany;
import com.lylastudio.gymflow.entity.MMember;
import com.lylastudio.gymflow.repository.MMemberRepository;
import com.lylastudio.gymflow.service.MemberService;
import com.lylastudio.gymflow.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MMemberRepository memberRepository;

    @Override
    public MemberResponse registerMember(MemberRequest request) {


        log.info("Register member request: {}", request);

        // Cek jika email sudah terdaftar
        memberRepository.findByEmail(request.getEmail()).ifPresent(m -> {
            throw new RuntimeException("validation.email.already.exists");
        });

        // Cek jika nomor HP sudah terdaftar
        memberRepository.findByPhoneNumber(request.getPhoneNumber()).ifPresent(m -> {
            throw new RuntimeException("validation.phone.already.exists");
        });

        String companyId = SecurityUtils.getCurrentCompanyId();
        MCompany company =new MCompany();
        company.setId(companyId);

        MMember member = MMember.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .dateOfBirth(request.getDateOfBirth())
                .membershipType(request.getMembershipType())
                .registeredAt(LocalDate.now())
                .address(request.getAddress())
                .company(company)
                .build();


        memberRepository.save(member);
        return mapToResponse(member);
//        MMember saved = memberRepository.save(member);
//
//        return MemberResponse.builder()
//                .id(saved.getId())
//                .fullName(saved.getFullName())
//                .email(saved.getEmail())
//                .phoneNumber(saved.getPhoneNumber())
//                .membershipType(saved.getMembershipType())
//                .registeredAt(saved.getRegisteredAt())
//                .build();
    }

    @Override
    public MemberResponse getMemberById(Long id) {
        MMember member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("member.not.found"));
        return mapToResponse(member);
    }

    @Override
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MemberResponse updateMember(Long id, MemberRequest request) {
        MMember member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("member.not.found"));

        member.setFullName(request.getFullName());
        member.setEmail(request.getEmail());
        member.setPhoneNumber(request.getPhoneNumber());
        member.setAddress(request.getAddress());

        memberRepository.save(member);
        return mapToResponse(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    private MemberResponse mapToResponse(MMember member) {
        return MemberResponse.builder()
                .id(member.getId())
                .fullName(member.getFullName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .address(member.getAddress())
                .membershipType(member.getMembershipType())
                .registeredAt(member.getRegisteredAt())
                .build();
    }
}
