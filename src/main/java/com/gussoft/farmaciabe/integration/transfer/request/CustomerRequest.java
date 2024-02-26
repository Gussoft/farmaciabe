package com.gussoft.farmaciabe.integration.transfer.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotNull(message = "debe proporcionar un nombre")
    private String name;
    @NotBlank(message = "debe proporcionar un apellido")
    private String lastName;
    private String sex;
    @Size(min = 8, max = 8)
    @NotBlank(message = "debe proporcionar 8 caracteres numericos")
    private String dni;

    @Size(min = 9, max = 12)
    @NotBlank(message = "debe proporcionar 9 caracteres numericos")
    private String phone;

    @Size(min = 11, max = 11)
    @NotBlank(message = "debe proporcionar 11 caracteres numericos")
    private String ruc;

    @Email(message = "deb proporcionar un Email Valido!")
    private String email;
    @NotBlank(message = "debe proporcionar una direccion")
    private String address;

    private Date birthDate;

    @NotNull(message = "No puede estar Vacio")
    private RegionRequest region;

}
