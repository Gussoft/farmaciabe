package com.gussoft.farmaciabe.core.models;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ac_sales_detail", schema = "medicine")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "sales_id")
    @JsonIgnore
    @ManyToOne
    private Sales sales;

    @JoinColumn(name = "product_id")
    @ManyToOne
    private Product product;

    private Integer cant;
    private BigDecimal cost;
    private BigDecimal price;
    private BigDecimal amount;

    public SalesDetail(Sales sales, Product product, Integer cant, BigDecimal cost,
                        BigDecimal price, BigDecimal amount) {
        this.sales = sales;
        this.product = product;
        this.cant = cant;
        this.cost = cost;
        this.price = price;
        this.amount = amount;
    }

}
