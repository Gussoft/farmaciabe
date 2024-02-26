package com.gussoft.farmaciabe.integration.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingRequest {

    //private String number;
    //private String date;
    private String typePay;
    private BigDecimal subTotal;
    private BigDecimal total;
    private BigDecimal igv;
    private String state;
    private Long supplier;
    private Long employee;
    private Long voucherType;
    private List<ShoppingDetailRequest> detail;

}
