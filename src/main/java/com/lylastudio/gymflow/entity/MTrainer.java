package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "m_trainer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MTrainer extends BaseEntityWithId {

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(unique = true, nullable = false, length = 20)
    private String phoneNumber;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    private LocalDate dateOfBirth;

    private String address;

    // Relation to Company (many members can belong to one company)
    @ManyToOne
    @JoinColumn(name = "company_id")
    private MCompany company;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MUser user;

}
