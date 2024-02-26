package com.gussoft.farmaciabe.integration.expose;

import com.gussoft.farmaciabe.core.business.EmployeeService;
import com.gussoft.farmaciabe.core.exception.InvalidParameterException;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.integration.mappers.EmployeeMapper;
import com.gussoft.farmaciabe.integration.transfer.request.EmployeeRequest;
import com.gussoft.farmaciabe.integration.transfer.response.EmployeeResponse;
import com.gussoft.farmaciabe.integration.transfer.response.GenericResponse;
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
public class EmployeeController {

    @Autowired
    private EmployeeMapper mapper;

    @Autowired
    private EmployeeService service;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<EmployeeResponse>> findAll() {
        return ResponseEntity.ok(mapper.toEmployeeListResponse(service.findAll()));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/employee/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<EmployeeResponse>> findByNameOrDni(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "dni", required = false) String dni)
            throws ResourceNotFoundException, InvalidParameterException {
        if (name != null) {
            return ResponseEntity.ok(mapper.toEmployeeListResponse(service.findByName(name)));
        } else if (dni != null) {
            return ResponseEntity.ok(mapper.toEmployeeListResponse(service.findByDni(dni)));
        }
        throw new InvalidParameterException("Name or Dni", "numeric", "Invalidate parameter request");
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<EmployeeResponse> findById(
            @PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toEmployeeResponse(service.findById(id)));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/v1/employee/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<EmployeeResponse> create(
            @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(mapper.toEmployeeResponse(service.save(request)));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/v1/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<EmployeeResponse> update(
            @PathVariable("id") long id,
            @RequestBody EmployeeRequest request) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toEmployeeResponse(service.update(request, id)));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/v1/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GenericResponse> delete(
            @PathVariable("id") long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.ok(new GenericResponse(200, "OK"));
    }

}
