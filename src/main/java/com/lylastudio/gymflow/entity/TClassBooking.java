package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_class_booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TClassBooking extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Foreign Key ke Branch (sisi Many)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_id", nullable = false)
    private TMembership membership;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_schedule_id", nullable = false)
    private TClassSchedule classSchedule;

    @Column(name = "description", length = 100)
    private String description;

}
