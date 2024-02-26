package com.gussoft.farmaciabe.integration.expose;

import com.gussoft.farmaciabe.core.business.SupplierService;
import com.gussoft.farmaciabe.core.exception.InvalidParameterException;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.integration.mappers.SupplierMapper;
import com.gussoft.farmaciabe.integration.transfer.request.SupplierRequest;
import com.gussoft.farmaciabe.integration.transfer.response.GenericResponse;
import com.gussoft.farmaciabe.integration.transfer.response.SupplierResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping
public class SupplierController {

    @Autowired
    private SupplierMapper mapper;

    @Autowired
    private SupplierService service;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/supplier", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<SupplierResponse>> findAll() {
        return ResponseEntity.ok(mapper.toSupplierListResponse(service.findAll()));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/supplier/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<SupplierResponse>> findByDni(
            @RequestParam(value = "nik", required = false) String dni,
            @RequestParam(value = "email", required = false) String name)
            throws ResourceNotFoundException, InvalidParameterException {
        if (dni != null) {
            return ResponseEntity.ok(mapper.toSupplierListResponse(service.findByDni(dni)));
        } else if (name != null) {
            return ResponseEntity.ok(mapper.toSupplierListResponse(service.findByName(name)));
        }
        throw new InvalidParameterException("Name or Dni", "numeric", "Invalidate parameter request");
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/v1/supplier/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SupplierResponse> create(
            @RequestBody SupplierRequest request) {
        return ResponseEntity.ok(mapper.toSupplierResponse(service.save(request)));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/v1/supplier/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<SupplierResponse> update(
            @PathVariable("id") long id,
            @RequestBody SupplierRequest request) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toSupplierResponse(service.update(request, id)));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/v1/supplier/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GenericResponse> delete(
            @PathVariable("id") long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.ok(new GenericResponse(200, "OK"));
    }

}
