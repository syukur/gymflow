package com.lylastudio.gymflow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "m_company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MCompany extends BaseEntityWithId{

    @Column(nullable = false)
    private String name;

    private String address;

    @Column(length = 20)
    private String phoneNumber;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch =  FetchType.LAZY)
    private List<MMember> members;
}

