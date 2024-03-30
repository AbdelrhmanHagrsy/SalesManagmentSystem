package com.example.Sales.Management.System.repository;

import com.example.Sales.Management.System.entity.SalesOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderLineRepository extends JpaRepository<SalesOrderLine,Long> {
}
