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
public class ShoppingDetailResponse {

    private Long id;
    @JsonIgnore
    private ShoppingResponse shopping;
    private ProductResponse product;
    private Integer cant;
    private BigDecimal price;
    private BigDecimal amount;

}
