package com.gussoft.farmaciabe.integration.mappers;

import com.gussoft.farmaciabe.core.models.Supplier;
import com.gussoft.farmaciabe.integration.transfer.response.SupplierResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierMapper {

    @Autowired
    ModelMapper mapper;

    public SupplierResponse toSupplierResponse(Supplier supplier) {
        return mapper.map(supplier, SupplierResponse.class);
    }

    public List<SupplierResponse> toSupplierListResponse(List<Supplier> suppliers) {
        return suppliers.stream()
                .map(supplier -> mapper.map(supplier, SupplierResponse.class))
                .collect(Collectors.toList());
    }

}
