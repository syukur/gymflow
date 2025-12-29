package com.lylastudio.gymflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    // Common fields and methods can be added here if needed
    // For example, an 'id' field if all entities share the same ID generation strategy
    // Or common audit fields like 'createdBy', 'updatedBy'


    @Column(updatable = false, name = "created_by")
    private String createBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private  String updateBy;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private  LocalDateTime updatedAt;
}
