package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_checkin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TCheckIn {
    @EmbeddedId
    private TCheckInId id;

    // Foreign Key ke Branch (sisi Many)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("branchId") // Memetakan field ini ke branchId di EmbeddedId
    @JoinColumn(name = "branch_id", nullable = false)
    private MBranch branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberId") // Memetakan field ini ke memberId di EmbeddedId
    @JoinColumn(name = "member_id", nullable = false)
    private MMember member;

    @Column(name = "check_in")
    private LocalDateTime checkIn;

    @Column(name = "check_out")
    private LocalDateTime checkOut;

    // --- CONVENIENCE CONSTRUCTOR ---
    public TCheckIn(MBranch branch, MMember member) {
        this.branch = branch;
        this.member = member;
        this.id = new TCheckInId(branch.getId(), member.getId());
    }

}
