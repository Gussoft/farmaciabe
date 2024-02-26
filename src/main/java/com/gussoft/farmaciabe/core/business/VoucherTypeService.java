package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.VoucherType;
import java.util.List;

public interface VoucherTypeService {

    List<VoucherType> findAll();

    List<VoucherType> findByDescription(String name) throws ResourceNotFoundException;

    VoucherType save(VoucherType obj);

    VoucherType update(VoucherType obj, Long id) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

}
