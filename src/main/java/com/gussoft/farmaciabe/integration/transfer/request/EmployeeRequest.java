package com.gussoft.farmaciabe.integration.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

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
