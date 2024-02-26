package com.gussoft.farmaciabe.integration.transfer.response;

import com.gussoft.farmaciabe.core.models.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private Users user;
    private String token;

}
