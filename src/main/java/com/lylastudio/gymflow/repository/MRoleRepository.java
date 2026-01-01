package com.lylastudio.gymflow.repository;

import com.lylastudio.gymflow.entity.MRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MRoleRepository extends JpaRepository<MRole, String> {
    Optional<MRole> findByName(String name);
}
