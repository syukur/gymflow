package com.lylastudio.gymflow.service;

import com.lylastudio.gymflow.dto.MemberRequest;
import com.lylastudio.gymflow.dto.MemberResponse;

import java.util.List;

public interface MemberService {
    MemberResponse registerMember(MemberRequest request);
    MemberResponse getMemberById(Long id);
    List<MemberResponse> getAllMembers();
    MemberResponse updateMember(Long id, MemberRequest request);
    void deleteMember(Long id);
}
