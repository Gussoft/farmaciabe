package com.gussoft.farmaciabe.integration.mappers;

import com.gussoft.farmaciabe.core.models.Customer;
import com.gussoft.farmaciabe.integration.transfer.request.CustomerRequest;
import com.gussoft.farmaciabe.integration.transfer.response.CustomerResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    @Autowired
    ModelMapper mapper;

    public CustomerResponse toCustomerResponse(Customer customer) {
        return mapper.map(customer, CustomerResponse.class);
    }

    public CustomerRequest toCustomerRequest(Customer customer) {
        return mapper.map(customer, CustomerRequest.class);
    }

    public List<CustomerResponse> toCustomerListResponse(List<Customer> customers) {
        return customers.stream()
                .map(customer -> mapper.map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
    }
    public Page<CustomerResponse> toCustomerPageResponse(Page<Customer> customers) {
        return customers.map(customer -> mapper.map(customer, CustomerResponse.class));
    }


}
