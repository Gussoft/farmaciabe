package com.gussoft.farmaciabe.core.business.impl;

import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;

import com.gussoft.farmaciabe.core.business.EmployeeService;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Employee;
import com.gussoft.farmaciabe.core.repository.EmployeeRepository;
import com.gussoft.farmaciabe.integration.transfer.request.EmployeeRequest;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee findById(Long id) throws ResourceNotFoundException {
        Optional<Employee> data = repository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        return data.get();
    }

    @Override
    public List<Employee> findByName(String name) throws ResourceNotFoundException {
        List<Employee> data = repository.findByName(name);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Name", name));
        }
        return data;
    }

    @Override
    public List<Employee> findByDni(String dni) throws ResourceNotFoundException {
        List<Employee> data = repository.findByDni(dni);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Dni", dni));
        }
        return data;
    }

    @Transactional
    @Override
    public Employee save(EmployeeRequest obj) {
        Employee employee = new Employee();
        employee.setName(obj.getName());
        employee.setLastName(obj.getLastName());
        employee.setEmail(obj.getEmail());
        employee.setDni(obj.getDni());
        employee.setAddress(obj.getAddress());
        employee.setPhone(obj.getPhone());
        employee.setSex(obj.getSex());
        employee.setHourEnd(obj.getHourEnd());
        employee.setHourStart(obj.getHourStart());
        employee.setSpecialty(obj.getSpecialty());
        employee.setState(obj.getState());

        return repository.save(employee);
    }

    @Transactional
    @Override
    public Employee update(EmployeeRequest obj, Long id) throws ResourceNotFoundException {
        Optional<Employee> data = repository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }

        Employee employee = data.get();
        employee.setName(obj.getName());
        employee.setLastName(obj.getLastName());
        employee.setEmail(obj.getEmail());
        employee.setDni(obj.getDni());
        employee.setAddress(obj.getAddress());
        employee.setPhone(obj.getPhone());
        employee.setSex(obj.getSex());
        employee.setHourEnd(obj.getHourEnd());
        employee.setHourStart(obj.getHourStart());
        employee.setSpecialty(obj.getSpecialty());
        employee.setState(obj.getState());

        return repository.save(employee);
    }

    @Transactional
    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Employee> data = repository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        repository.deleteById(id);
    }

}
