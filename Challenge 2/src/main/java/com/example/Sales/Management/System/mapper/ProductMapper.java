package com.example.Sales.Management.System.mapper;

import com.example.Sales.Management.System.dto.ProductDto;
import com.example.Sales.Management.System.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductDto productDto){

        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .availableQuantity(productDto.getQuantity())
                .description(productDto.getDescription())
                .build();
        if (productDto.getId() != null)
            product.setId(productDto.getId());
        return product;
    }

    public ProductDto toDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .quantity(product.getAvailableQuantity())
                .name(product.getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .description(product.getDescription())
                .createdBy(product.getCreatedBy())
                .createdDate(product.getCreatedDate())
                .build();
    }
}
