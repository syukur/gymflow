package com.lylastudio.gymflow.controller;

import com.lylastudio.gymflow.repository.MCompanyRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HelloController {

    private final MCompanyRepository companyRepository;

    @Operation(description = "Hello Word for testing")
    @GetMapping("/api/hello")
    public String hello() {

        companyRepository.findAll().forEach(company -> {
            log.info("Company: {}, {}", company.getName(), company.getId());
//            company.getMembers().forEach(member -> {
//                log.info("Member: {}, {}", member.getFullName(), member.getId());
//
//            });
        });
        return "Hello World";
    }
}
