package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_class_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TClassSchedule extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Foreign Key ke Branch (sisi Many)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trainer_id", nullable = false)
    private MTrainer trainer;

    @OneToOne(mappedBy = "classSchedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private TClassSession classSession;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_type_id", nullable = false)
    private MClassType classType;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "booked_count")
    private int bookedCount;

    @Column(name = "start_training")
    private LocalDateTime startTraining;

    @Column(name = "end_training")
    private LocalDateTime endTraining;

    @Column(name = "description", length = 100)
    private String description;



}
