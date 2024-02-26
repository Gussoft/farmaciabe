package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.Laboratory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory, Long> {

    @Query("SELECT l FROM Laboratory l WHERE LOWER(l.name) LIKE LOWER(concat('%', :name, '%'))")
    List<Laboratory> findByName(String name);

}
