package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_product")
public class MProduct extends BaseEntityWithId{

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "description", length = 150)
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private MCompany company;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private MProductCategory productCategory;


}
