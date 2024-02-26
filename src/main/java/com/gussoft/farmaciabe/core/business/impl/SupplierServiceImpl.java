package com.gussoft.farmaciabe.core.business.impl;

import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;

import com.gussoft.farmaciabe.core.business.SupplierService;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Supplier;
import com.gussoft.farmaciabe.core.repository.SupplierRepository;
import com.gussoft.farmaciabe.integration.transfer.request.SupplierRequest;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository repository;

    @Override
    public List<Supplier> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Supplier> findByName(String name) throws ResourceNotFoundException {
        List<Supplier> supplier = repository.findByName(name);
        if (supplier == null) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Name", name));
        }
        return supplier;
    }

    @Override
    public List<Supplier> findByDni(String dni) throws ResourceNotFoundException {
        List<Supplier> supplier = repository.findByDni(dni);
        if (supplier == null) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Dni", dni));
        }
        return supplier;
    }

    @Override
    public Supplier save(SupplierRequest obj) {
        Supplier supplier = new Supplier();
        supplier.setName(obj.getName());
        supplier.setDni(obj.getDni());
        supplier.setEmail(obj.getEmail());
        supplier.setAddress(obj.getAddress());
        supplier.setPhone(obj.getPhone());
        supplier.setRuc(obj.getRuc());
        supplier.setBank(obj.getBank());
        supplier.setAccount(obj.getAccount());
        supplier.setState(obj.getState());
        return repository.save(supplier);
    }

    @Override
    public Supplier update(SupplierRequest obj, Long id) throws ResourceNotFoundException {
        Optional<Supplier> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        Supplier supplier = data.get();
        supplier.setName(obj.getName());
        supplier.setDni(obj.getDni());
        supplier.setEmail(obj.getEmail());
        supplier.setAddress(obj.getAddress());
        supplier.setPhone(obj.getPhone());
        supplier.setRuc(obj.getRuc());
        supplier.setBank(obj.getBank());
        supplier.setAccount(obj.getAccount());
        supplier.setState(obj.getState());
        return repository.save(supplier);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Supplier> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        repository.deleteById(id);
    }

}
