package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_role")
public class MRole extends BaseEntityWithId{

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "description", length = 150)
    private String description;

//    @ManyToOne
//    @JoinColumn(name = "company_id")
//    private MCompany company;

    // Relasi One-to-Many ke Entity Penghubung (RoleEndpoint)
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TRoleEnpoint> roleEnpoints;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TUserInvitation> invitations;

}
