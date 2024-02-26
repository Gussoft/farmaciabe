package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.description) LIKE LOWER(concat('%', :description, '%'))")
    List<Product> findByDescription(String description);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(concat('%', :name, '%'))")
    List<Product> findByName(String name);

}
