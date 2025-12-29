package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "t_class_template")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TClassTemplate extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Foreign Key ke Branch (sisi Many)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private MTrainer trainer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_type_id", nullable = false)
    private MClassType classType;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "description", length = 100)
    private String description;

    // Relasi One-to-Many ke Detail
    @OneToMany(mappedBy = "classTemplate", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TClassTemplateDetail> details;


}
