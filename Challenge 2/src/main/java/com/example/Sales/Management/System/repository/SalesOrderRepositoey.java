package com.example.Sales.Management.System.repository;

import com.example.Sales.Management.System.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderRepositoey  extends JpaRepository<SalesOrder,Long> {
}
