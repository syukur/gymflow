package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_sales_order")
public class TSalesOrder extends BaseEntityWithId{

    @ManyToOne
    @JoinColumn(name = "company_id")
    private MCompany company;

    @Column(name = "order_number", nullable = false, unique = true, length = 50)
    private String orderNumber;

    @ManyToOne
    @JoinColumn(name = "cashier_id")
    private MUser cashier;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private MMember customer;

    @Comment("1: PAID, 2: CANCELED")
    @ManyToOne
    @JoinColumn(name = "status_id")
    private MOrderStatus status;

    @Column(name = "description", length = 100)
    private String note;

    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<TSalesOrderDetail> orderDetails;

}
