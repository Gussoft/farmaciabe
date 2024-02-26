package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.Customer;
import com.gussoft.farmaciabe.core.models.Region;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT e FROM Customer e WHERE e.dni LIKE %:dni%")
    List<Customer> findByDni(String dni);

    @Query("SELECT e FROM Customer e WHERE e.ruc LIKE %:ruc%")
    List<Customer> findByRuc(String ruc);

    @Query("FROM Region")
    List<Region> findAllRegions();

}
