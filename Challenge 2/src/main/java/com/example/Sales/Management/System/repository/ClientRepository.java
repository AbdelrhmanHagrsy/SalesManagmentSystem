package com.example.Sales.Management.System.repository;

import com.example.Sales.Management.System.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
