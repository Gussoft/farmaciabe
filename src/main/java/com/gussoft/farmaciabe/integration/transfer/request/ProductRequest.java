package com.gussoft.farmaciabe.integration.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String name;
    private String description;
    private String concentration;
    private Integer stock;
    private BigDecimal priceBuy;
    private BigDecimal priceSale;
    private String registerHealth;
    private String expirationDate;
    private String state;
    private Long presentation;
    private Long laboratory;

}
