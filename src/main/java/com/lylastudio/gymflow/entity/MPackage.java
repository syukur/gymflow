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
@Table(name = "m_package")
public class MPackage extends BaseEntityWithId {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 150)
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "gym_access")
    private boolean gymAccess;

    @Column(name = "include_pt")
    private boolean includePt;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private MCompany company;

}
