package com.gussoft.farmaciabe.core.business.impl;

import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;

import com.gussoft.farmaciabe.core.business.PresentationService;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Presentation;
import com.gussoft.farmaciabe.core.repository.PresentationRepository;
import com.gussoft.farmaciabe.integration.transfer.request.PresentationRequest;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PresentationServiceImpl implements PresentationService {

    @Autowired
    private PresentationRepository repository;

    @Override
    public List<Presentation> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Presentation> findByName(String description) throws ResourceNotFoundException {
        List<Presentation> presentations = repository.findByName(description);
        if (presentations == null) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "description", description));
        }
        return presentations;
    }

    @Override
    public Presentation save(PresentationRequest obj) {
        Presentation presentation = new Presentation();
        presentation.setDescription(obj.getDescription());
        presentation.setState(obj.getState());
        return repository.save(presentation);
    }

    @Override
    public Presentation update(PresentationRequest obj, Long id) throws ResourceNotFoundException {
        Optional<Presentation> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        Presentation presentation = data.get();
        presentation.setDescription(obj.getDescription());
        presentation.setState(obj.getState());
        return repository.save(presentation);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Presentation> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        repository.deleteById(id);
    }

}
