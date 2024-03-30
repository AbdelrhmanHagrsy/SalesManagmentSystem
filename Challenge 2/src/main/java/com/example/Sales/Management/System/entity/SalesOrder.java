package com.example.Sales.Management.System.entity;

import com.example.Sales.Management.System.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salesorder")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesOrder extends BaseEntity<Long> {


    @Column
    BigDecimal total;

    @Column
    String sellerName;

    @ManyToOne
    @JoinColumn(name = "client_id",referencedColumnName = "id")
    Client client;

    @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
    List<SalesOrderLine> salesOrderLines = new ArrayList<>();
}
