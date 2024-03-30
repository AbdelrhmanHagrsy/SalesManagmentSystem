package com.example.Sales.Management.System.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OrderLineDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("order_id")
    private Long salesOrderID;

    @JsonProperty("tax")
    private BigDecimal tax;

    @JsonProperty("discount")
    private BigDecimal discount;

    @JsonProperty("saled_quintity")
    private BigDecimal saledQuintity;

    @JsonProperty("total_line_price")
    private BigDecimal totalPrice;

    @JsonProperty("product")
    private ProductDto product;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("created_by")
    private String createdBy;

    @JsonProperty("updated_at")
    private String updatedAt;

}
