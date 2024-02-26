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
public class ShoppingResponse {

    private Long id;

    private String number;
    private String date;
    private String typePay;
    private BigDecimal subTotal;
    private BigDecimal total;
    private BigDecimal igv;
    private String state;

    private SupplierResponse supplier;

    private EmployeeResponse employee;

    private VoucherType voucherType;

    private List<ShoppingDetailResponse> detail;

}
