package com.gussoft.farmaciabe.core.business.impl;

import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;

import com.gussoft.farmaciabe.core.business.ProductService;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Laboratory;
import com.gussoft.farmaciabe.core.models.Presentation;
import com.gussoft.farmaciabe.core.models.Product;
import com.gussoft.farmaciabe.core.repository.LaboratoryRepository;
import com.gussoft.farmaciabe.core.repository.PresentationRepository;
import com.gussoft.farmaciabe.core.repository.ProductRepository;
import com.gussoft.farmaciabe.integration.transfer.request.ProductRequest;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    @Autowired
    private PresentationRepository presentationRepository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findByName(String name) throws ResourceNotFoundException {
        List<Product> data = repository.findByName(name);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Name", name));
        }
        return data;
    }

    @Override
    public List<Product> findByDescription(String description) throws ResourceNotFoundException {
        List<Product> data = repository.findByDescription(description);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Description", description));
        }
        return data;
    }

    @Override
    @Transactional
    public Product save(ProductRequest request) throws ResourceNotFoundException {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setConcentration(request.getConcentration());
        product.setStock(request.getStock());
        product.setLaboratory(getLaboratoryById(request.getLaboratory()));
        product.setPresentation(getPresentationById(request.getPresentation()));
        product.setExpirationDate(request.getExpirationDate());
        product.setPriceBuy(request.getPriceBuy());
        product.setPriceSale(request.getPriceSale());
        product.setRegisterHealth(request.getRegisterHealth());
        product.setState(request.getState());
        return repository.save(product);
    }

    @Override
    @Transactional
    public Product update(ProductRequest request, Long id) throws ResourceNotFoundException {
        Optional<Product> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        Product product = data.get();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setConcentration(request.getConcentration());
        product.setStock(request.getStock());
        product.setLaboratory(getLaboratoryById(request.getLaboratory()));
        product.setPresentation(getPresentationById(request.getPresentation()));
        product.setExpirationDate(request.getExpirationDate());
        product.setPriceBuy(request.getPriceBuy());
        product.setPriceSale(request.getPriceSale());
        product.setRegisterHealth(request.getRegisterHealth());
        product.setState(request.getState());
        return repository.save(product);
    }

    @Transactional
    @Override
    public void updateStock(Long id, Integer stock, String operation) {
        Optional<Product> data = repository.findById(id);
        if (!data.isPresent()) {
            return;
        }
        Product product = data.get();
        if (operation.equals("Sales")) {
            product.setStock(product.getStock() - stock);
        } else if(operation.equals("Shop")){
            product.setStock(product.getStock() + stock);
        }
        repository.save(product);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Product> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        repository.deleteById(id);
    }

    private Laboratory getLaboratoryById(Long id) throws ResourceNotFoundException {
        Optional<Laboratory> laboratory = laboratoryRepository.findById(id);
        if (laboratory.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Id Laboratory", id));
        }
        return laboratory.get();
    }

    private Presentation getPresentationById(Long id) throws ResourceNotFoundException {
        Optional<Presentation> presentation = presentationRepository.findById(id);
        if (presentation.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Id Presentation", id));
        }
        return presentation.get();
    }
}
