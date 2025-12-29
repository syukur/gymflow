package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_endpoint")
public class MEnpoint extends BaseEntityWithId {

    @Column(name = "endpoint", nullable = false)
    private String username;

    @Column(name = "description", length = 100)
    private String description;

}
