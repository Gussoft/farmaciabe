package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.Shopping;
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
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

    @Query("SELECT s FROM Shopping s WHERE s.date BETWEEN :dateInit AND :dateEnd ORDER BY s.date")
    List<Shopping> findByDateOnRange(
            @Param("dateInit") LocalDateTime dateInit,
            @Param("dateEnd") LocalDateTime dateEnd);

    @Query("SELECT s FROM Shopping s WHERE s.date LIKE %:date%")
    List<Shopping> findByOneDate(LocalDateTime date);

    @Query("SELECT s FROM Shopping s WHERE s.number LIKE %:number%")
    List<Shopping> findByNumberSeries(String number);

    @Query("SELECT MAX(s.number) FROM Shopping s")
    String countUltimateNumber();

    @Transactional
    @Modifying
    @Query("update Shopping s set s.typePay=:typePay, s.subTotal=:subTotal, s.total=:total," +
            " s.igv=:igv, s.state=:state where s.id=:id")
    void update(
            @Param("id") Long id,
            @Param("typePay") String typePay,
            @Param("subTotal") BigDecimal subTotal,
            @Param("total") BigDecimal total,
            @Param("igv") BigDecimal igv,
            @Param("state") String state
    );

}
