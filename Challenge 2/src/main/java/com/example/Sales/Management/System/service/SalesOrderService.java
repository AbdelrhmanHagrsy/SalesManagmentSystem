package com.example.Sales.Management.System.service;

import com.example.Sales.Management.System.dto.OrderDto;
import com.example.Sales.Management.System.dto.OrderLineDto;
import com.example.Sales.Management.System.dto.ProductDto;
import com.example.Sales.Management.System.entity.Client;
import com.example.Sales.Management.System.entity.Product;
import com.example.Sales.Management.System.entity.SalesOrder;
import com.example.Sales.Management.System.entity.SalesOrderLine;
import com.example.Sales.Management.System.exception.RecordNotFoundException;
import com.example.Sales.Management.System.mapper.OrderMapper;
import com.example.Sales.Management.System.repository.ProductRepository;
import com.example.Sales.Management.System.repository.SalesOrderLineRepository;
import com.example.Sales.Management.System.repository.SalesOrderRepositoey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SalesOrderService {

    private final SalesOrderRepositoey salesOrderRepositoey;

    private final SalesOrderLineRepository salesOrderLineRepository;
    private final ProductRepository productRepository;

    private final OrderMapper orderMapper;

    @Autowired
    MessageSource messageSource;

    @Autowired
    public SalesOrderService(SalesOrderRepositoey salesOrderRepositoey, SalesOrderLineRepository salesOrderLineRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.salesOrderRepositoey = salesOrderRepositoey;
        this.salesOrderLineRepository = salesOrderLineRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    private SalesOrder saveOrderWithOrderLines(SalesOrder order) {
        for (SalesOrderLine orderLine : order.getSalesOrderLines()) {
            orderLine.setSalesOrder(order); // Set the Order for each OrderLine
        }
        return salesOrderRepositoey.save(order);
    }
    private SalesOrder priceEngine(SalesOrder salesOrder){

        BigDecimal totalPriceLine;
        BigDecimal orderTotalPrice = new BigDecimal("0");
        Product product ;
        List<SalesOrderLine> salesOrderLineList = salesOrder.getSalesOrderLines();
            for(SalesOrderLine salesOrderLine : salesOrderLineList){
                //
                product = productRepository.findById(salesOrderLine.getProduct().getId()).get();
                totalPriceLine =  product.getPrice().multiply(salesOrderLine.getSaledQuintity());
                totalPriceLine = totalPriceLine.add(salesOrderLine.getTax());
                totalPriceLine = totalPriceLine.subtract(salesOrderLine.getDiscount());
                // set total price for each line in each line
                salesOrderLine.setTotalLinePrice(totalPriceLine);
                // calculate total price for all lines
                orderTotalPrice = orderTotalPrice.add(totalPriceLine);
                totalPriceLine = new BigDecimal("0");
            }
        // set total lines price in order
        salesOrder.setSalesOrderLines(salesOrderLineList);
        salesOrder.setTotal(orderTotalPrice);

        return salesOrder;
    }


    public Long createOrder(OrderDto  orderDto) {
        try {
            // prepare order price
            SalesOrder salesOrder = orderMapper.toEntity(orderDto);
            salesOrder = priceEngine(salesOrder);
            //
            salesOrder  = saveOrderWithOrderLines(salesOrder);
            if (salesOrder != null) {
                log.info("Order :"+ salesOrder.getId() + " created successfully");
                return salesOrder.getId();
            }
        } catch (Exception e) {
            log.error("Error while creating Order: {}", e.getMessage());
        }
        throw new RuntimeException("Failed to create Order.");
    }


    public OrderDto findOne(Long id) {

        Optional<SalesOrder> salesOrder = salesOrderRepositoey.findById(id);
        if(salesOrder.isEmpty()){
            String [] para = {id.toString()};
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", para, LocaleContextHolder.getLocale()));
        }
        return orderMapper.toDto(salesOrder.get());
    }

    public Long updateOrder(Long id, OrderDto orderDto) {
        Optional<SalesOrder> salesOrder = salesOrderRepositoey.findById(id);
        if(salesOrder.isEmpty()){
            String [] para = {id.toString()};
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", para, LocaleContextHolder.getLocale()));
        }
        return  salesOrderRepositoey.save(priceEngine(orderMapper.toEntity(orderDto))).getId();
    }

    public boolean deleteOrder(Long id) {
        Optional<SalesOrder> salesOrder = salesOrderRepositoey.findById(id);
        if (salesOrder.isEmpty()) {
            String[] para = {id.toString()};
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", para, LocaleContextHolder.getLocale()));
        }
        salesOrderRepositoey.deleteById(id);
        log.info("Order with ID :"+ id + " has deleted successfully");
        return true;
    }
}
