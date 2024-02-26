package com.gussoft.farmaciabe.core.business.impl;

import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;

import com.gussoft.farmaciabe.core.business.CustomerService;
import com.gussoft.farmaciabe.core.business.StorageService;
import com.gussoft.farmaciabe.core.exception.FileException;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Customer;
import com.gussoft.farmaciabe.core.models.Region;
import com.gussoft.farmaciabe.core.repository.CustomerRepository;
import com.gussoft.farmaciabe.integration.transfer.request.CustomerRequest;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private StorageService storage;

    @Override
    public Page<Customer> findAll(Pageable pages) {
        return repository.findAll(pages);
    }


    @Override
    public Customer findById(Long id) throws ResourceNotFoundException {
        Optional<Customer> data = repository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        return data.get();
    }

    @Override
    public List<Customer> findByDni(String dni) throws ResourceNotFoundException {
        List<Customer> obj = repository.findByDni(dni);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Dni", dni));
        }
        return obj;
    }

    @Override
    public List<Customer> findByRuc(String ruc) throws ResourceNotFoundException {
        List<Customer> obj = repository.findByRuc(ruc);
        if (obj.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Ruc", ruc));
        }
        return obj;
    }

    @Override
    public Customer save(CustomerRequest obj) {
        Customer customer = new Customer();
        customer.setName(obj.getName());
        customer.setLastName(obj.getLastName());
        customer.setDni(obj.getDni());
        customer.setRuc(obj.getRuc());
        customer.setEmail(obj.getEmail());
        customer.setSex(obj.getSex());
        customer.setPhone(obj.getPhone());
        customer.setAddress(obj.getAddress());
        customer.setBirthDate(obj.getBirthDate());
        customer.setRegion(new Region(obj.getRegion().getId(), obj.getRegion().getName()));

        return repository.save(customer);
    }

    @Override
    public Customer update(CustomerRequest obj, Long id) throws ResourceNotFoundException {
        Optional<Customer> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        Customer customer = data.get();
        customer.setName(obj.getName());
        customer.setLastName(obj.getLastName());
        customer.setDni(obj.getDni());
        customer.setRuc(obj.getRuc());
        customer.setEmail(obj.getEmail());
        customer.setSex(obj.getSex());
        customer.setPhone(obj.getPhone());
        customer.setAddress(obj.getAddress());
        customer.setBirthDate(obj.getBirthDate());
        customer.setRegion(new Region(obj.getRegion().getId(), obj.getRegion().getName()));

        return repository.save(customer);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException, FileException {
        Optional<Customer> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        Customer customer = data.get();
        if (customer.getPhoto() != null) {
            storage.deleteImage(customer.getPhoto());
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void uploadImage(Long id, String path) throws ResourceNotFoundException, FileException {
        Optional<Customer> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        Customer customer = data.get();
        if (customer.getPhoto() != null) {
            storage.deleteImage(customer.getPhoto());
        }
        customer.setPhoto(path);
        repository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Region> findAllRegions() {
        return repository.findAllRegions();
    }

}
