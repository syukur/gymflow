package com.lylastudio.gymflow.repository;

import com.lylastudio.gymflow.entity.MMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MMemberRepository extends JpaRepository<MMember, Long> {
    Optional<MMember> findByEmail(String email);
    Optional<MMember> findByPhoneNumber(String phoneNumber);
}
