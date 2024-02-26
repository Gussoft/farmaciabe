package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Product;
import com.gussoft.farmaciabe.integration.transfer.request.ProductRequest;
import java.util.List;

public interface ProductService {

    List<Product> findAll();

    List<Product> findByName(String name) throws ResourceNotFoundException;

    List<Product> findByDescription(String description) throws ResourceNotFoundException;

    Product save(ProductRequest request) throws ResourceNotFoundException;

    Product update(ProductRequest request, Long id) throws ResourceNotFoundException;

    void updateStock(Long id, Integer stock, String operation);

    void delete(Long id) throws ResourceNotFoundException;

}
