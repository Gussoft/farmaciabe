package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE LOWER(e.name) LIKE LOWER(concat('%', :name, '%'))")
    List<Employee> findByName(String name);

    @Query("SELECT e FROM Employee e WHERE e.dni LIKE %:dni%")
    List<Employee> findByDni(String dni);

}
