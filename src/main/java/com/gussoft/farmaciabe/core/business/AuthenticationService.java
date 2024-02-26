package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.models.Users;
import com.gussoft.farmaciabe.integration.transfer.request.UsersRequest;
import com.gussoft.farmaciabe.integration.transfer.response.LoginResponse;

public interface AuthenticationService {

    Users registerUser(UsersRequest request);

    LoginResponse loginUser(String username, String password);

}
