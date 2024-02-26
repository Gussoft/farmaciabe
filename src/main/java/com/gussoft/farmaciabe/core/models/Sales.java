package com.gussoft.farmaciabe.core.models;

import static jakarta.persistence.FetchType.EAGER;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@Table(name = "ac_sales", schema = "medicine")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String series;
    private String number;
    private LocalDateTime date;
    private BigDecimal salesTotal;
    private BigDecimal discount;
    private BigDecimal subTotal; // this.price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
    private BigDecimal total;
    private BigDecimal igv;
    private String state;

    @JoinColumn(name = "customer_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonIgnore
    private Customer customer;

    @JoinColumn(name = "employee_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonIgnore
    private Employee employee;

    @JoinColumn(name = "voucher_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JsonIgnore
    private VoucherType voucherType;

    @OneToMany(mappedBy = "sales", fetch = EAGER)
    private List<SalesDetail> detail;

}
