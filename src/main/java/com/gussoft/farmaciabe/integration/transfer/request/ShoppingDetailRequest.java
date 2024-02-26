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
public class ShoppingDetailRequest {

    private Long id;
    private Long shopping;
    private Long product;
    private Integer cant;
    private BigDecimal price;
    private BigDecimal amount;

}
