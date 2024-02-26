package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.VoucherType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherTypeRepository extends JpaRepository<VoucherType, Long> {

    @Query("SELECT v FROM VoucherType v WHERE LOWER(v.description) LIKE LOWER(concat('%', :description, '%'))")
    List<VoucherType> findByDescription(String description);

}
