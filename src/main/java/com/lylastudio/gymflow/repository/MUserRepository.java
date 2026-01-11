package com.lylastudio.gymflow.repository;

import com.lylastudio.gymflow.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MUserRepository extends JpaRepository<MUser, String> {
    Optional<MUser> findByUsername(String username);

    Optional<MUser> findByEmail(String email);

}
