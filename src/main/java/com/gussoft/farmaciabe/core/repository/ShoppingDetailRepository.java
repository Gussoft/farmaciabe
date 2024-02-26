package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.ShoppingDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingDetailRepository extends JpaRepository<ShoppingDetail, Long> {

    @Modifying
    @Query("delete from ShoppingDetail d where d.shopping.id =:id")
    @Transactional
    void deleteByShoppingId(@Param("id") Long id);

}
