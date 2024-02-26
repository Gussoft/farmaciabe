package com.gussoft.farmaciabe.core.models;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ac_supplier", schema = "medicine")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
