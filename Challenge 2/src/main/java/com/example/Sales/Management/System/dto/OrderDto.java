package com.example.Sales.Management.System.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("seller_name")
    private String seller;

    @JsonProperty("order_total_price")
    private BigDecimal orderTotalPrice;

    @JsonProperty("Client")
    private ClientDto client;

    @JsonProperty("order_lines")
    private List<OrderLineDto> orderLineDtoList;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_at")
    private String updatedAt;
}
