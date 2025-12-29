package com.lylastudio.gymflow.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntityWithId extends  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
