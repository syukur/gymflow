package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "m_company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MCompany extends BaseEntityWithId{

    @Column(nullable = false, length = 100)
    private String name;

    private String address;

    @Column(length = 20)
    private String phoneNumber;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch =  FetchType.LAZY)
    private List<MMember> members;
}

