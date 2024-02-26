package com.gussoft.farmaciabe.core.business.impl;

import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;

import com.gussoft.farmaciabe.core.business.LaboratoryService;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Laboratory;
import com.gussoft.farmaciabe.core.repository.LaboratoryRepository;
import com.gussoft.farmaciabe.integration.transfer.request.LaboratoryRequest;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    private LaboratoryRepository repository;

    @Override
    public List<Laboratory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Laboratory> findByName(String name) throws ResourceNotFoundException {
        List<Laboratory> laboratories = repository.findByName(name);
        if (laboratories == null) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Name", name));
        }
        return laboratories;
    }

    @Override
    public Laboratory findById(Long id) throws ResourceNotFoundException {
        Optional<Laboratory> data = repository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        return data.get();
    }

    @Transactional
    @Override
    public Laboratory save(LaboratoryRequest obj) {
        Laboratory laboratory = new Laboratory();
        laboratory.setName(obj.getName());
        laboratory.setAddress(obj.getAddress());
        laboratory.setPhone(obj.getPhone());
        laboratory.setState(obj.getState());
        return repository.save(laboratory);
    }

    @Transactional
    @Override
    public Laboratory update(LaboratoryRequest obj, Long id) throws ResourceNotFoundException {
        Optional<Laboratory> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        Laboratory laboratory = data.get();
        laboratory.setName(obj.getName());
        laboratory.setAddress(obj.getAddress());
        laboratory.setPhone(obj.getPhone());
        laboratory.setState(obj.getState());
        return repository.save(laboratory);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Laboratory> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        repository.deleteById(id);
    }

}
