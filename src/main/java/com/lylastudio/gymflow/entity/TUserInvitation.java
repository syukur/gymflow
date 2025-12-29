package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user_invitation")
public class TUserInvitation extends BaseEntityWithId{

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "invite_by", length = 150)
    private String inviteBy;

    @Column(name = "expired", nullable = false)
    private LocalDateTime expired;

    @Column(name = "status", nullable = false, length = 20)
    private String status;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private MCompany company;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private MRole role;
}
