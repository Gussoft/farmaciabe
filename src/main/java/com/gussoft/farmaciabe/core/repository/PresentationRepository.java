package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.Presentation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Long> {

    @Query("SELECT p FROM Presentation p WHERE LOWER(p.description) LIKE LOWER(concat('%', :description, '%'))")
    List<Presentation> findByName(String description);

}
