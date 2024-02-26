package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Employee;
import com.gussoft.farmaciabe.core.models.Users;
import com.gussoft.farmaciabe.integration.transfer.request.EmployeeRequest;
import com.gussoft.farmaciabe.integration.transfer.request.UsersRequest;
import java.util.List;

public interface UserService {

    List<Users> findAll();

    List<Users> findByEmail(String email) throws ResourceNotFoundException;

    List<Users> findByNik(String nik) throws ResourceNotFoundException;

    Users save(UsersRequest obj);

    Users update(UsersRequest obj, Long id) throws ResourceNotFoundException;

    void addEmployeeUser(Long id, Employee employee)  throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

}
