package com.gussoft.farmaciabe.integration.mappers;

import com.gussoft.farmaciabe.core.models.Shopping;
import com.gussoft.farmaciabe.integration.transfer.response.ShoppingResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingMapper {

    @Autowired
    ModelMapper mapper;

    public ShoppingResponse toShoppingResponse(Shopping shop) {
        return mapper.map(shop, ShoppingResponse.class);
    }

    public List<ShoppingResponse> toShoppingListResponse(List<Shopping> shopping) {
        return shopping.stream()
                .map(shop -> mapper.map(shop, ShoppingResponse.class))
                .collect(Collectors.toList());
    }

}
