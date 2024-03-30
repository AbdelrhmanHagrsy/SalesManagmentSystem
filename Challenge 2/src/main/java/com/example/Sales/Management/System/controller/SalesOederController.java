package com.example.Sales.Management.System.controller;

import com.example.Sales.Management.System.dto.OrderDto;
import com.example.Sales.Management.System.dto.OrderLineDto;
import com.example.Sales.Management.System.service.SalesOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class SalesOederController {

    private final SalesOrderService salesOrderService;

    public SalesOederController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @PostMapping( "/order")
    public ResponseEntity<?> createProduct(@RequestBody OrderDto orderDto) throws Exception{
        Long id = salesOrderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salesOrderService.findOne(id));
    }

    @GetMapping( "/order/{id}")
    public ResponseEntity<?> createProduct(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(salesOrderService.findOne(id));
    }

    @PutMapping( "/order/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody OrderDto orderDto) throws Exception{
        Long productId = salesOrderService.updateOrder(id, orderDto);
        return ResponseEntity.status(HttpStatus.OK).body(salesOrderService.findOne(productId));
    }

    @DeleteMapping( "/order/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) throws Exception{
        salesOrderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }
}
