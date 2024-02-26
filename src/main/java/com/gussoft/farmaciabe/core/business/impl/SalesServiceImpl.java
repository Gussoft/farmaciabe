package com.gussoft.farmaciabe.core.business.impl;

import static com.gussoft.farmaciabe.core.utils.Constrains.FORMAT_DATE;
import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;
import static com.gussoft.farmaciabe.core.utils.Util.getLocalDateNow;

import com.gussoft.farmaciabe.core.business.ProductService;
import com.gussoft.farmaciabe.core.business.SalesService;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Customer;
import com.gussoft.farmaciabe.core.models.Employee;
import com.gussoft.farmaciabe.core.models.Product;
import com.gussoft.farmaciabe.core.models.VoucherType;
import com.gussoft.farmaciabe.core.models.Sales;
import com.gussoft.farmaciabe.core.models.SalesDetail;
import com.gussoft.farmaciabe.core.repository.CustomerRepository;
import com.gussoft.farmaciabe.core.repository.EmployeeRepository;
import com.gussoft.farmaciabe.core.repository.ProductRepository;
import com.gussoft.farmaciabe.core.repository.SalesRepository;
import com.gussoft.farmaciabe.core.repository.SalesDetailRepository;
import com.gussoft.farmaciabe.core.repository.VoucherTypeRepository;
import com.gussoft.farmaciabe.integration.transfer.request.SalesRequest;
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
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private SalesDetailRepository detailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VoucherTypeRepository voucherRepository;

    @Override
    public List<Sales> findAll() {
        return salesRepository.findAll();
    }

    @Override
    public List<Sales> findByNumberSeries(String series) throws ResourceNotFoundException {
        List<Sales> data = salesRepository.findByNumberSeries(series);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Series", series));
        }
        return data;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Sales> findByDateRange(String date1, String  date2) throws ResourceNotFoundException {
        List<Sales> data;
        if (date2 != null && date1 != null) {
            LocalDateTime init = LocalDateTime.parse(date1, DateTimeFormatter.ofPattern(FORMAT_DATE));
            LocalDateTime end = LocalDateTime.parse(date2, DateTimeFormatter.ofPattern(FORMAT_DATE));
            data = salesRepository.findByDateOnRange(init, end);
            //data = salesRepository.findByDateBetween(init, end);
        } else if (date2 == null && date1 != null) {
            LocalDateTime init = LocalDateTime.parse(date1, DateTimeFormatter.ofPattern(FORMAT_DATE));
            data = salesRepository.findByOneDate(init);
        } else {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Range", "Range Date Invalid"));
        }
        return data;
    }

    @Transactional
    @Override
    public Sales save(SalesRequest request) throws ResourceNotFoundException {
        Sales sales = new Sales();
        sales.setDate(getLocalDateNow());
        sales.setSeries(request.getSeries());
        sales.setSalesTotal(request.getSalesTotal());
        sales.setDiscount(request.getDiscount());
        sales.setSubTotal(request.getSubTotal());
        sales.setTotal(request.getTotal());
        sales.setIgv(request.getIgv());
        sales.setState(request.getState());
        sales.setVoucherType(getVoucherTypeById(request.getVoucherId()));
        sales.setEmployee(getEmployeeById(request.getEmployeeId()));
        sales.setCustomer(getCustomerById(request.getCustomerId()));
        sales.setNumber(generateSerialNumber());
        Sales saved = salesRepository.save(sales);
        List<SalesDetail> details = request.getDetail()
                .stream()
                .map(item -> {
                    SalesDetail d = new SalesDetail(saved, getProductById(item.getProductId()), item.getCant(),
                        item.getCost(), item.getPrice(), item.getAmount());
                    productService.updateStock(item.getProductId(), item.getCant(), "Sales");
                    return d;
                    }
                )
                .collect(Collectors.toList());
        detailRepository.saveAll(details);
        saved.setDetail(details);
        return salesRepository.findById(saved.getId()).orElse(null);
    }

    @Transactional
    @Override
    public Sales update(SalesRequest request, Long id) throws ResourceNotFoundException {
        salesRepository.update(id, request.getSalesTotal(), request.getDiscount(), request.getSubTotal(),
                                    request.getTotal(),request.getIgv(), request.getState());
        Optional<Sales> data = salesRepository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Id Sales", id));
        }
        detailRepository.deleteBySalesId(id);
        Sales sales = data.get();

        List<SalesDetail> details = request.getDetail()
                .stream()
                .map(item -> new SalesDetail(sales, getProductById(item.getProductId()), item.getCant(), item.getCost(),
                                item.getPrice(), item.getAmount()))
                .collect(Collectors.toList());
        detailRepository.saveAll(details);
        sales.setDetail(details);
        return salesRepository.findById(data.get().getId()).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Sales> data = salesRepository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Id Sales", id));
        }
        detailRepository.deleteBySalesId(id);
        salesRepository.deleteById(id);
    }

    @Override
    public String generateSerialNumber() {
        String lastNumber = salesRepository.countUltimateNumber();
        if (lastNumber == null) {
            return "V000001";
        } else {
            int count = Integer.parseInt(lastNumber.substring(1));
            if (count >= 100000) {
                return "V" + String.format("%06d", 1);
            } else {
                return "V" + String.format("%06d", count + 1);
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

    private Customer getCustomerById(Long id) throws ResourceNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException(
                    MessageFormat.format(MESSAGE_NOT_FOUND, "Id Customer", id));
        }
        return customer.get();
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
