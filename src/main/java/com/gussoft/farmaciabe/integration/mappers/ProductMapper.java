package com.gussoft.farmaciabe.integration.mappers;

import com.gussoft.farmaciabe.core.models.Product;
import com.gussoft.farmaciabe.integration.transfer.response.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    ModelMapper mapper;

    public ProductResponse toProductResponse(Product product) {
        return mapper.map(product, ProductResponse.class);
    }

    public List<ProductResponse> toProductListResponse(List<Product> products) {
        return products.stream()
                .map(product -> mapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

}
