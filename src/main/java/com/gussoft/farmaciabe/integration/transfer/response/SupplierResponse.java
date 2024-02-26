package com.gussoft.farmaciabe.integration.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponse {

    private Long id;
    private String name;
    private String dni;
    private String ruc;
    private String address;
    private String email;
    private String phone;
    private String bank;
    private String account;
    private String state;

}
