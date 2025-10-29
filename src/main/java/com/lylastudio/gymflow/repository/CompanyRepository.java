package com.lylastudio.gymflow.repository;

import com.lylastudio.gymflow.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}

