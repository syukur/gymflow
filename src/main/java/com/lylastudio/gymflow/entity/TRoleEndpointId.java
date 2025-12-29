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
public class TRoleEndpointId implements Serializable {
    private String roleId;
    private String endpointId;
}
