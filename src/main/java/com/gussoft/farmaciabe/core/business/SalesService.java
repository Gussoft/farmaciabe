package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Sales;
import com.gussoft.farmaciabe.integration.transfer.request.SalesRequest;
import java.util.List;

public interface SalesService {

    List<Sales> findAll();

    List<Sales> findByNumberSeries(String series) throws ResourceNotFoundException;

    List<Sales> findByDateRange(String date1, String  date2) throws ResourceNotFoundException;

    Sales save(SalesRequest request) throws ResourceNotFoundException;

    Sales update(SalesRequest request, Long id) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

    String generateSerialNumber();

}
