package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Employee;
import com.gussoft.farmaciabe.integration.transfer.request.EmployeeRequest;
import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(Long id) throws ResourceNotFoundException;

    List<Employee> findByName(String name) throws ResourceNotFoundException;

    List<Employee> findByDni(String dni) throws ResourceNotFoundException;

    Employee save(EmployeeRequest request);

    Employee update(EmployeeRequest request, Long id) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

}
