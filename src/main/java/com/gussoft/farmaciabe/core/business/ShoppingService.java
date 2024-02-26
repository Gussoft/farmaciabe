package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Shopping;
import com.gussoft.farmaciabe.integration.transfer.request.ShoppingRequest;
import java.util.List;

public interface ShoppingService {

    List<Shopping> findAll();

    List<Shopping> findByNumberSeries(String series) throws ResourceNotFoundException;

    List<Shopping> findByDateRange(String date1, String  date2) throws ResourceNotFoundException;

    Shopping save(ShoppingRequest request) throws ResourceNotFoundException;

    Shopping update(ShoppingRequest request, Long id) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

    String generateSerialNumber();

}
