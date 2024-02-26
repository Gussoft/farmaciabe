package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.FileException;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Customer;
import com.gussoft.farmaciabe.core.models.Region;
import com.gussoft.farmaciabe.integration.transfer.request.CustomerRequest;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<Customer> findAll(Pageable pages);

    Customer findById(Long id) throws ResourceNotFoundException;

    List<Customer> findByDni(String dni) throws ResourceNotFoundException;

    List<Customer> findByRuc(String ruc) throws ResourceNotFoundException;

    Customer save(CustomerRequest obj);

    Customer update(CustomerRequest obj, Long id) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException, FileException;

    void uploadImage(Long id, String path) throws ResourceNotFoundException, IOException, FileException;

    List<Region> findAllRegions();

}
