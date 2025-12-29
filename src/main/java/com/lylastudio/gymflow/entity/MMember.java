package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "m_member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MMember extends BaseEntityWithId {

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(unique = true, nullable = false, length = 20)
    private String phoneNumber;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    private LocalDate dateOfBirth;

    private String membershipType; // Contoh: "Basic", "Premium", "VIP"

    private LocalDate registeredAt;

    private String address;

    // Relation to Company (many members can belong to one company)
    @ManyToOne
    @JoinColumn(name = "company_id")
    private MCompany company;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TCheckIn> checkIns;

}
