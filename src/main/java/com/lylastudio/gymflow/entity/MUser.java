package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_user")
public class MUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "email")
    private String email;

    @Column(name = "auth_provider")
    private String authProvider;

    @Column(name = "sub")
    private String sub;


}
