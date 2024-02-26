package com.gussoft.farmaciabe.integration.transfer.response;

import com.gussoft.farmaciabe.core.models.VoucherType;
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
public class SalesResponse {

    private Long id;
    private String series;
    private String number;
    private String date;
    private BigDecimal salesTotal;
    private BigDecimal discount;
    private BigDecimal subTotal;
    private BigDecimal total;
    private BigDecimal igv;
    private String state;
    private EmployeeResponse employee;
    private CustomerResponse customer;
    private VoucherType voucherType;
    private List<SalesDetailResponse> detail;

}
