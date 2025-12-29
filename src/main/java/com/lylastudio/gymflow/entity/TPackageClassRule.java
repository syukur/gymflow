package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_package_class_rule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TPackageClassRule {
    @EmbeddedId
    private TPackageClassRuleId id;

    // Foreign Key ke Branch (sisi Many)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("packageId") // Memetakan field ini ke branchId di EmbeddedId
    @JoinColumn(name = "package_id", nullable = false)
    private MPackage mPackage;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("classTypeId") // Memetakan field ini ke memberId di EmbeddedId
    @JoinColumn(name = "class_type_id", nullable = false)
    private MClassType classType;

    @Column(name = "max_sessions")
    private int max_sessions;


    // --- CONVENIENCE CONSTRUCTOR ---
    public TPackageClassRule(MPackage mPackage, MClassType classType) {
        this.mPackage = mPackage;
        this.classType = classType;
        this.id = new TPackageClassRuleId(mPackage.getId(), classType.getId());
    }

}
