package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_role_endpoint")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TRoleEnpoint extends  BaseEntity {
    @EmbeddedId
    private TRoleEndpointId id;

    // Foreign Key ke Mrole (sisi Many)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("roleId") // Memetakan field ini ke roleId di EmbeddedId
    @JoinColumn(name = "role_id", nullable = false)
    private MRole role;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("endpointId") // Memetakan field ini ke endpointId di EmbeddedId
    @JoinColumn(name = "endpoint", nullable = false)
    private MEnpoint endpoint;

    // --- CONVENIENCE CONSTRUCTOR ---
    public TRoleEnpoint(MRole role, MEnpoint endpoint) {
        this.role = role;
        this.endpoint = endpoint;
        this.id = new TRoleEndpointId(role.getId(), endpoint.getId());
    }

}
