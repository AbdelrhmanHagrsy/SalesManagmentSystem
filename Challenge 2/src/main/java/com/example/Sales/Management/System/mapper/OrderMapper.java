package com.example.Sales.Management.System.mapper;

import com.example.Sales.Management.System.dto.OrderDto;
import com.example.Sales.Management.System.dto.OrderLineDto;
import com.example.Sales.Management.System.entity.SalesOrder;
import com.example.Sales.Management.System.entity.SalesOrderLine;
import com.example.Sales.Management.System.repository.ClientRepository;
import com.example.Sales.Management.System.repository.SalesOrderRepositoey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ClientMapper clientMapper;

    private final OrderLineMapper orderLineMapper;

    private  final ClientRepository clientRepository;

    private final SalesOrderRepositoey salesOrderRepositoey;


    @Autowired
    public OrderMapper(ClientMapper clientMapper, OrderLineMapper orderLineMapper, ClientRepository clientRepository, SalesOrderRepositoey salesOrderRepositoey) {
        this.clientMapper = clientMapper;
        this.orderLineMapper = orderLineMapper;
        this.clientRepository = clientRepository;
        this.salesOrderRepositoey = salesOrderRepositoey;
    }

    public OrderDto toDto(SalesOrder salesOrder){
        // create  order lines dto
        List<OrderLineDto> orderLineDtoList = salesOrder.getSalesOrderLines().stream().map(x ->orderLineMapper.toDto(x)).collect(Collectors.toList());
        return OrderDto.builder()
                .id(salesOrder.getId())
                .client(clientMapper.toDto(salesOrder.getClient()))
                .orderLineDtoList(orderLineDtoList)
                .seller(salesOrder.getSellerName())
                .orderTotalPrice(salesOrder.getTotal())
                .createdAt(salesOrder.getCreatedDate())
                .createdBy(salesOrder.getCreatedBy())
                .updatedAt(salesOrder.getLastModifiedDate())
                .build();
    }

    public SalesOrder toEntity(OrderDto orderDto) {

        // get order id in case update order
        SalesOrder salesOrderOld = orderDto.getId() == null ? null : salesOrderRepositoey.findById(orderDto.getId()).get();
        // create  order lines dto
        List<SalesOrderLine> salesOrderLines = orderDto.getOrderLineDtoList().stream().map(x -> orderLineMapper.toEntiy(x)).collect(Collectors.toList());
        //
        SalesOrder salesOrder = SalesOrder.builder()
                .sellerName(orderDto.getSeller())
                .client(clientRepository.findById(orderDto.getClient().getId()).get())
                .salesOrderLines(salesOrderLines)
                .build();
        //
        if (salesOrderOld != null) {
            salesOrder.setId(salesOrderOld.getId());
            salesOrder.setCreatedBy(salesOrderOld.getCreatedBy());
            salesOrder.setCreatedDate(salesOrderOld.createdDate);
        }
        return salesOrder;
    }
}
