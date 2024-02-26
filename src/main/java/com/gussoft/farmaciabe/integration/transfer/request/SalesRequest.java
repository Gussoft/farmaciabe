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
public class SalesRequest {

    private String series;

    //private String number;

    //private String date;
    private BigDecimal salesTotal;
    private BigDecimal discount;
    private BigDecimal subTotal;
    private BigDecimal total;
    private BigDecimal igv;
    private String state;
    private Long employeeId;
    private Long customerId;
    private Long voucherId;
    private List<SalesDetailRequest> detail;

}
