package com.gussoft.farmaciabe.integration.mappers;

import com.gussoft.farmaciabe.core.models.Employee;
import com.gussoft.farmaciabe.integration.transfer.request.EmployeeRequest;
import com.gussoft.farmaciabe.integration.transfer.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    @Autowired
    ModelMapper mapper;

    public EmployeeResponse toEmployeeResponse(Employee employee) {
        return mapper.map(employee, EmployeeResponse.class);
    }

    public List<EmployeeResponse> toEmployeeListResponse(List<Employee> employees) {
        return employees.stream()
                .map(employee -> mapper.map(employee, EmployeeResponse.class))
                .collect(Collectors.toList());
    }

    public Employee toEmployeeToRequest(EmployeeRequest request) {
        return mapper.map(request, Employee.class);
    }

}
