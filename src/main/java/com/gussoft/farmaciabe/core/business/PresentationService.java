package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Presentation;
import com.gussoft.farmaciabe.integration.transfer.request.PresentationRequest;
import java.util.List;

public interface PresentationService {

    List<Presentation> findAll();

    List<Presentation> findByName(String name) throws ResourceNotFoundException;

    Presentation save(PresentationRequest presentation);

    Presentation update(PresentationRequest presentation, Long id) throws ResourceNotFoundException;

    void delete(Long id)throws ResourceNotFoundException;

}
