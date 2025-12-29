package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "m_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MMember extends BaseEntityWithId {

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
    private MCompany company;
}
