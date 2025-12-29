package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "t_class_template_detail")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TClassTemplateDetail extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Relasi Many-to-One ke Header Template
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_template_id", nullable = false)
    private TClassTemplate classTemplate; // Nama field ini sesuai dengan mappedBy di TClassTemplate

    @Column(nullable = false)
    private Integer capacity;

    // Kolom ini sebaiknya menggunakan ENUM atau Integer (0-6)
    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;
}
