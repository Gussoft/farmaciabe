package com.gussoft.farmaciabe.core.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ac_shopping", schema = "medicine")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shopping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private LocalDateTime date;
    private String typePay;
    private BigDecimal subTotal; // this.price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
    private BigDecimal total;
    private BigDecimal igv;
    private String state;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Supplier supplier;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Employee employee;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private VoucherType voucherType;

    @OneToMany(mappedBy = "shopping", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ShoppingDetail> detail;

}
