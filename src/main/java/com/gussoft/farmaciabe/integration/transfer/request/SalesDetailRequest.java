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
public class SalesDetailRequest {

    private Long id;
    private Integer cant;
    private BigDecimal cost;
    private BigDecimal price;
    private BigDecimal amount;
    private Long salesId;
    private Long productId;

}
