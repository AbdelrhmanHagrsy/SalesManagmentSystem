package com.example.Sales.Management.System.controller;

import com.example.Sales.Management.System.dto.ProductDto;
import com.example.Sales.Management.System.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping( "/product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) throws Exception{
        Long id = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.findOne(id));
    }

    @GetMapping( "/product/{id}")
    public ResponseEntity<?> createProduct(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(productService.findOne(id));
    }

    @PutMapping( "/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody ProductDto productDto) throws Exception{
        Long productId = productService.updateProduct(id,productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productService.findOne(productId));
    }

    @DeleteMapping( "/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) throws Exception{
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }
}
