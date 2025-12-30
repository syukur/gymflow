package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "t_class_session_detail")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TClassSessionDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Relasi Many-to-One ke Header Template
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private MMember member; // Nama field ini sesuai dengan mappedBy di TClassTemplate

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_session_id", nullable = false)
    private TClassSession classSession; // Nama field ini sesuai dengan mappedBy di TClassTemplate

}
