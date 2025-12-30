package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "t_class_session")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TClassSession extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_schedule_id")
    private TClassSchedule classSchedule;

    @Column(name = "actual_start_training")
    private LocalDateTime actualStartTraining;

    @Column(name = "actual_end_training")
    private LocalDateTime actualEndTraining;

    @Column(name = "description", length = 100)
    private String description;

    @OneToMany(mappedBy = "classSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TClassSessionDetail> classSessionDetails;
}
