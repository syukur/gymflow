package com.lylastudio.gymflow.controller;

import com.lylastudio.gymflow.dto.ApiResponse;
import com.lylastudio.gymflow.dto.MemberRequest;
import com.lylastudio.gymflow.dto.MemberResponse;
import com.lylastudio.gymflow.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lylastudio.gymflow.util.ApiResponseUtil;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ApiResponseUtil responseUtil;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<MemberResponse>> registerMember(@Valid @RequestBody MemberRequest request) {
        MemberResponse response = memberService.registerMember(request);

        return ResponseEntity.ok(
                responseUtil.success("member.register.success", response)
        );

    }
}

