package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(unique = true, nullable = false)
    private String email;

    private LocalDate dateOfBirth;

    private String membershipType; // Contoh: "Basic", "Premium", "VIP"

    private LocalDate registeredAt;

    private String address;

    // Relation to Company (many members can belong to one company)
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
