package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Comment("example: 3 month regular, 6 month regular")
@Table(name = "m_package")
@Builder
public class MPackage extends BaseEntityWithId {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 150)
    private String description;

    @Comment("example : 30 day, 60 day, 90 day")
    @Column(name = "duration")
    private int duration;

    @Column(name = "gym_access")
    private boolean gymAccess;

    @Column(name = "price")
    private long price;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private MCompany company;

}
