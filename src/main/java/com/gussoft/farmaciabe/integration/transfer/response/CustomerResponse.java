package com.gussoft.farmaciabe.integration.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private Long id;
    private String name;
    private String lastName;
    private String sex;
    private String dni;
    private String phone;
    private String ruc;
    private String email;
    private String address;

    private Date birthDate;

    private String photo;

    private RegionResponse region;

}
