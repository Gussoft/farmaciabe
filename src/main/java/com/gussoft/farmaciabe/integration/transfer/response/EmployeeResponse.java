package com.gussoft.farmaciabe.integration.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String name;
    private String lastName;
    private String specialty;
    private String sex;
    private String dni;
    private String email;
    private String phone;
    private String address;
    private String hourStart;
    private String hourEnd;
    private String state;

}
