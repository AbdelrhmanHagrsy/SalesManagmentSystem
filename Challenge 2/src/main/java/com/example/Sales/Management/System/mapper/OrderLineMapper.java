package com.example.Sales.Management.System.mapper;

import com.example.Sales.Management.System.dto.OrderLineDto;
import com.example.Sales.Management.System.entity.SalesOrderLine;
import com.example.Sales.Management.System.repository.ProductRepository;
import com.example.Sales.Management.System.repository.SalesOrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderLineMapper {

    private final ProductRepository productRepository;

    private final  ProductMapper productMapper;

    private final SalesOrderLineRepository salesOrderLineRepository;

    @Autowired
    public OrderLineMapper(ProductRepository productRepository, ProductMapper productMapper, SalesOrderLineRepository salesOrderLineRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.salesOrderLineRepository = salesOrderLineRepository;
    }

    public OrderLineDto toDto(SalesOrderLine salesOrderLine){
         return OrderLineDto.builder()
                 .id(salesOrderLine.getId())
                 .product(productMapper.toDto(salesOrderLine.getProduct()))
                 .tax(salesOrderLine.getTax())
                 .discount(salesOrderLine.getDiscount())
                 .totalPrice(salesOrderLine.getTotalLinePrice())
                 .saledQuintity(salesOrderLine.getSaledQuintity())
                 .salesOrderID(salesOrderLine.getSalesOrder() == null ? 0 :  salesOrderLine.getSalesOrder().getId())
                 .createdAt(salesOrderLine.getCreatedDate())
                 .createdBy(salesOrderLine.getCreatedBy())
                 .updatedAt(salesOrderLine.getLastModifiedDate())
                 .build();
    }

    public SalesOrderLine toEntiy(OrderLineDto orderLineDto){
        SalesOrderLine salesOrderLineOld  = orderLineDto.getId() == null ? null : salesOrderLineRepository.findById(orderLineDto.getId()).get();
        SalesOrderLine salesOrderLine = SalesOrderLine.builder()
                .product(productRepository.findById(orderLineDto.getProduct().getId()).get())
                .discount(orderLineDto.getDiscount())
                .saledQuintity(orderLineDto.getSaledQuintity())
                .tax(orderLineDto.getTax())
                .totalLinePrice(orderLineDto.getTotalPrice())
                .build();
        if(salesOrderLineOld != null) {
            salesOrderLine.setId(salesOrderLineOld.getId());
            salesOrderLine.setCreatedBy(salesOrderLineOld.createdBy);
            salesOrderLine.setCreatedDate(salesOrderLineOld.createdDate);
        }
        return  salesOrderLine;

    }
}
