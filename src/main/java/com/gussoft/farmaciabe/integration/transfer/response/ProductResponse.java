package com.gussoft.farmaciabe.integration.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private String concentration;
    private Integer stock;
    private BigDecimal priceBuy;
    private BigDecimal priceSale;
    private String registerHealth;
    private String expirationDate;
    private String state;
    private PresentationResponse presentation;
    private LaboratoryResponse laboratory;

}
