package com.example.Sales.Management.System.entity;

import com.example.Sales.Management.System.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Client extends BaseEntity<Long> {

    @Column
    String name;
    @Column
    String lastName;
    @Column
    String mobile;
    @Column
    String email;
    @Column
    String address;

}
