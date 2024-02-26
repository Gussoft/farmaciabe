package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Supplier;
import com.gussoft.farmaciabe.integration.transfer.request.SupplierRequest;
import java.util.List;

public interface SupplierService {

    List<Supplier> findAll();

    List<Supplier> findByName(String name) throws ResourceNotFoundException;

    List<Supplier> findByDni(String dni) throws ResourceNotFoundException;

    Supplier save(SupplierRequest request);

    Supplier update(SupplierRequest request, Long id) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

}
