package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Laboratory;
import com.gussoft.farmaciabe.integration.transfer.request.LaboratoryRequest;
import java.util.List;

public interface LaboratoryService {

    List<Laboratory> findAll();

    List<Laboratory> findByName(String name) throws ResourceNotFoundException;

    Laboratory findById(Long id) throws ResourceNotFoundException;

    Laboratory save(LaboratoryRequest laboratory);

    Laboratory update(LaboratoryRequest laboratory, Long id) throws ResourceNotFoundException;

    void delete(Long id) throws ResourceNotFoundException;

}
