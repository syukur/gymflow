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
@Table(name = "m_user")
public class MUser extends BaseEntityWithId{

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "sure_name", nullable = false, length = 100)
    private String sureName;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "email",nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "auth_provider")
    private String authProvider;

    @Column(name = "sub", length = 100)
    private String sub;

    // Relation to Company (many User can belong to one company)
    @ManyToOne
    @JoinColumn(name = "company_id")
    private MCompany company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private MTrainer trainerProfile;

    @OneToMany(mappedBy = "cashier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TSalesOrder> salesOrders;
}
