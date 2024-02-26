package com.gussoft.farmaciabe.core.business.impl;

import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;

import com.gussoft.farmaciabe.core.business.VoucherTypeService;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.VoucherType;
import com.gussoft.farmaciabe.core.repository.VoucherTypeRepository;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoucherTypeServiceImpl implements VoucherTypeService {

    @Autowired
    private VoucherTypeRepository repository;

    @Override
    public List<VoucherType> findAll() {
        return repository.findAll();
    }

    @Override
    public List<VoucherType> findByDescription(String description) throws ResourceNotFoundException {
        List<VoucherType> vouchers = repository.findByDescription(description);
        if (vouchers == null) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Nik", description));
        }
        return vouchers;
    }

    @Override
    public VoucherType save(VoucherType obj) {
        VoucherType voucherType = new VoucherType();
        voucherType.setDescription(obj.getDescription());
        voucherType.setState(obj.getState());

        return repository.save(voucherType);
    }

    @Override
    public VoucherType update(VoucherType obj, Long id) throws ResourceNotFoundException {
        Optional<VoucherType> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        VoucherType voucherType = data.get();
        voucherType.setDescription(obj.getDescription());
        voucherType.setState(obj.getState());
        return repository.save(voucherType);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<VoucherType> data = repository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        repository.deleteById(id);
    }

}
