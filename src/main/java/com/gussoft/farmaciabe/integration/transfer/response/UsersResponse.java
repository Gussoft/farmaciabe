package com.gussoft.farmaciabe.integration.transfer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersResponse {

    private Long id;
    private String username;
    private String password;
    private String subsidiary;
    private String state;

}
