package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.SalesDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesDetailRepository extends JpaRepository<SalesDetail, Long> {

    @Modifying
    @Query("delete from SalesDetail d where d.sales.id =:id")
    @Transactional
    void deleteBySalesId(@Param("id") Long id);

}
