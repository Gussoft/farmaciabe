package com.gussoft.farmaciabe.integration.mappers;

import com.gussoft.farmaciabe.core.models.Sales;
import com.gussoft.farmaciabe.integration.transfer.response.SalesResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalesMapper {

    @Autowired
    ModelMapper mapper;

    public SalesResponse toSalesResponse(Sales sales) {
        return mapper.map(sales, SalesResponse.class);
    }

    public List<SalesResponse> toSalesListResponse(List<Sales> sales) {
        return sales.stream()
                .map(sale -> mapper.map(sale, SalesResponse.class))
                .collect(Collectors.toList());
    }

}
