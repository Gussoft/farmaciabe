package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.Supplier;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT s FROM Supplier s WHERE LOWER(s.name) LIKE LOWER(concat('%', :name, '%'))")
    List<Supplier> findByName(String name);

    @Query("SELECT s FROM Supplier s WHERE s.dni LIKE %:dni%")
    List<Supplier> findByDni(String dni);

}
