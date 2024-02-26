package com.gussoft.farmaciabe.integration.transfer.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDetailResponse {

    private Long id;
    private Integer cant;
    private BigDecimal cost;
    private BigDecimal price;
    private BigDecimal amount;
    @JsonIgnore
    private SalesResponse sales;
    private ProductResponse product;

}
