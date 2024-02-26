package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.Sales;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {

    @Query("SELECT s FROM Sales s WHERE s.date BETWEEN :dateInit AND :dateEnd ORDER BY s.date")
    List<Sales> findByDateOnRange(
            @Param("dateInit") LocalDateTime dateInit,
            @Param("dateEnd") LocalDateTime dateEnd);

    @Query("SELECT s FROM Sales s WHERE s.date LIKE %:date%")
    List<Sales> findByOneDate(LocalDateTime date);

    @Query("SELECT s FROM Sales s WHERE s.number LIKE %:number%")
    List<Sales> findByNumberSeries(String number);

    @Query("SELECT MAX(s.number) FROM Sales s")
    String countUltimateNumber();

    @Transactional
    @Modifying
    @Query("update Sales s set s.salesTotal=:salesTotal, s.discount=:discount, s.subTotal=:subTotal," +
            " s.total=:total, s.igv=:igv, s.state=:state where s.id=:id")
    void update(
            @Param("id") Long id,
            @Param("salesTotal") BigDecimal salesTotal,
            @Param("discount") BigDecimal discount,
            @Param("subTotal") BigDecimal subTotal,
            @Param("total") BigDecimal total,
            @Param("igv") BigDecimal igv,
            @Param("state") String state
    );

}
