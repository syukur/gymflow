package com.lylastudio.gymflow.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TClassTemplateId implements Serializable {
    private String classTypeId;
    private String trainerId;
}
