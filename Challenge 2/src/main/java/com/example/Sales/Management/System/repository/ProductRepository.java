package com.example.Sales.Management.System.repository;

import com.example.Sales.Management.System.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
