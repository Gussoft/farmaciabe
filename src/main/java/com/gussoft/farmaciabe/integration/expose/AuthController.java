package com.gussoft.farmaciabe.integration.expose;

import com.gussoft.farmaciabe.core.business.AuthenticationService;
import com.gussoft.farmaciabe.core.models.Users;
import com.gussoft.farmaciabe.integration.transfer.request.LoginRequest;
import com.gussoft.farmaciabe.integration.transfer.request.UsersRequest;
import com.gussoft.farmaciabe.integration.transfer.response.GenericResponse;
import com.gussoft.farmaciabe.integration.transfer.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody UsersRequest request) {
        return authenticationService.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        LoginResponse response = authenticationService.loginUser(request.getUsername(), request.getPassword());
        if (response.getToken().equals("")) {
            return ResponseEntity.badRequest().body(response);
        }
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + response.getToken());
        return ResponseEntity.ok().headers(header).body(response);
    }

    @GetMapping("/logoutprueba")
    public ResponseEntity<GenericResponse> logout() {

        return ResponseEntity.ok(new GenericResponse(HttpStatus.OK.value(), "OK"));
    }
}
