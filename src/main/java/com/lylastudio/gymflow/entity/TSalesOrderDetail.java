package com.lylastudio.gymflow.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_sales_order_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TSalesOrderDetail {
    @EmbeddedId
    private TSalesOrderId id;

    // Foreign Key ke Branch (sisi Many)
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("salesOrderId") // Memetakan field ini ke branchId di EmbeddedId
    @JoinColumn(name = "sales_order_id", nullable = false)
    private TSalesOrder salesOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId") // Memetakan field ini ke memberId di EmbeddedId
    @JoinColumn(name = "product_id", nullable = false)
    private MProduct product;

    @Column(name = "actual_cost_price")
    private int actualCostPrice;

    @Column(name = "actual_selling_price")
    private int actualSellingPrice;

    @Column(name = "is_void")
    private boolean isVoid;


    // --- CONVENIENCE CONSTRUCTOR ---
    public TSalesOrderDetail(TSalesOrder salesOrder, MProduct product) {
        this.salesOrder = salesOrder;
        this.product = product;
        this.id = new TSalesOrderId(salesOrder.getId(), product.getId());
    }

}
