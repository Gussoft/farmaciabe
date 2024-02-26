package com.gussoft.farmaciabe.core.business.impl;

import static com.gussoft.farmaciabe.core.utils.Constrains.FORMAT_DATE;
import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;
import static com.gussoft.farmaciabe.core.utils.Util.getLocalDateNow;

import com.gussoft.farmaciabe.core.business.ProductService;
import com.gussoft.farmaciabe.core.business.ShoppingService;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Employee;
import com.gussoft.farmaciabe.core.models.Product;
import com.gussoft.farmaciabe.core.models.VoucherType;
import com.gussoft.farmaciabe.core.models.Shopping;
import com.gussoft.farmaciabe.core.models.ShoppingDetail;
import com.gussoft.farmaciabe.core.models.Supplier;
import com.gussoft.farmaciabe.core.repository.EmployeeRepository;
import com.gussoft.farmaciabe.core.repository.ProductRepository;
import com.gussoft.farmaciabe.core.repository.ShoppingRepository;
import com.gussoft.farmaciabe.core.repository.ShoppingDetailRepository;
import com.gussoft.farmaciabe.core.repository.SupplierRepository;
import com.gussoft.farmaciabe.core.repository.VoucherTypeRepository;
import com.gussoft.farmaciabe.integration.transfer.request.ShoppingRequest;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private ShoppingDetailRepository detailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private VoucherTypeRepository voucherRepository;

    @Override
    public List<Shopping> findAll() {
        return shoppingRepository.findAll();
    }

    @Override
    public List<Shopping> findByNumberSeries(String series) throws ResourceNotFoundException {
        List<Shopping> data = shoppingRepository.findByNumberSeries(series);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Series", series));
        }
        return data;
    }

    @Override
    public List<Shopping> findByDateRange(String date1, String date2) throws ResourceNotFoundException {
        List<Shopping> data;
        if (date2 != null && date1 != null) {
            LocalDateTime init = LocalDateTime.parse(date1, DateTimeFormatter.ofPattern(FORMAT_DATE));
            LocalDateTime end = LocalDateTime.parse(date2, DateTimeFormatter.ofPattern(FORMAT_DATE));

            data = shoppingRepository.findByDateOnRange(init, end);
        } else if (date2 == null && date1 != null) {
            LocalDateTime init = LocalDateTime.parse(date1, DateTimeFormatter.ofPattern(FORMAT_DATE));
            data = shoppingRepository.findByOneDate(init);
        } else {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Range", "Range Date Invalid"));
        }
        return data;
    }

    @Transactional
    @Override
    public Shopping save(ShoppingRequest request) throws ResourceNotFoundException {
        Shopping shopping = new Shopping();
        shopping.setNumber(generateSerialNumber());
        shopping.setDate(getLocalDateNow());
        shopping.setTypePay(request.getTypePay());
        shopping.setSubTotal(request.getSubTotal());
        shopping.setTotal(request.getTotal());
        shopping.setIgv(request.getIgv());
        shopping.setState(request.getState());
        shopping.setEmployee(getEmployeeById(request.getEmployee()));
        shopping.setSupplier(getSupplierById(request.getSupplier()));
        shopping.setVoucherType(getVoucherTypeById(request.getVoucherType()));
        Shopping saved = shoppingRepository.save(shopping);
        List<ShoppingDetail> details = request.getDetail()
                .stream()
                .map(item -> {
                     ShoppingDetail d = new ShoppingDetail(saved, getProductById(item.getProduct()), item.getCant(),
                                                            item.getPrice(), item.getAmount());
                     productService.updateStock(item.getProduct(), item.getCant(), "Shop");
                     return d;
                    }
                )
                .collect(Collectors.toList());
        detailRepository.saveAll(details);
        saved.setDetail(details);
        return shoppingRepository.findById(saved.getId()).orElse(null);
    }

    @Transactional
    @Override
    public Shopping update(ShoppingRequest request, Long id) throws ResourceNotFoundException {
        shoppingRepository.update(id, request.getTypePay(), request.getSubTotal(),
                            request.getTotal(), request.getIgv(), request.getState());
        Optional<Shopping> data = shoppingRepository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Id Shopping", id));
        }
        detailRepository.deleteByShoppingId(id);
        Shopping shopping = data.get();

        List<ShoppingDetail> details = request.getDetail()
                .stream()
                .map(item -> new ShoppingDetail(shopping, getProductById(item.getProduct()),
                        item.getCant(), item.getPrice(), item.getAmount()))
                .collect(Collectors.toList());
        detailRepository.saveAll(details);
        shopping.setDetail(details);
        return shoppingRepository.findById(shopping.getId()).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Shopping> data = shoppingRepository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Id Shopping", id));
        }
        detailRepository.deleteByShoppingId(id);
        shoppingRepository.deleteById(id);
    }

    @Override
    public String generateSerialNumber() {
        String lastNumber = shoppingRepository.countUltimateNumber();
        if (lastNumber == null) {
            return "S000001";
        } else {
            int count = Integer.parseInt(lastNumber.substring(1));
            if (count >= 100000) {
                return "S" + String.format("%06d", 1);
            } else {
                return "S" + String.format("%06d", count + 1);
            }
        }
    }

    private Employee getEmployeeById(Long id) throws ResourceNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Id Employee", id));
        }
        return employee.get();
    }

    private Supplier getSupplierById(Long id) throws ResourceNotFoundException {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Id Supplier", id));
        }
        return supplier.get();
    }

    private Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }

    private VoucherType getVoucherTypeById(Long id) throws ResourceNotFoundException {
        Optional<VoucherType> voucher = voucherRepository.findById(id);
        if (voucher.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Id VoucherType", id));
        }
        return voucher.get();
    }
}
