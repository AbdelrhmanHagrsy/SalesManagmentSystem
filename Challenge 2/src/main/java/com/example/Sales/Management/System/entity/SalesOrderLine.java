package com.example.Sales.Management.System.entity;

import com.example.Sales.Management.System.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "salesorderline")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesOrderLine extends BaseEntity<Long> {

    @Column
    BigDecimal saledQuintity;
    @Column
    BigDecimal tax;
    @Column
    BigDecimal discount;
    @Column
    BigDecimal totalLinePrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;



}
