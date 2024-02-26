package com.gussoft.farmaciabe.integration.expose;

import com.gussoft.farmaciabe.core.business.LaboratoryService;
import com.gussoft.farmaciabe.core.exception.InvalidParameterException;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.integration.mappers.LaboratoryMapper;
import com.gussoft.farmaciabe.integration.transfer.request.LaboratoryRequest;
import com.gussoft.farmaciabe.integration.transfer.response.GenericResponse;
import com.gussoft.farmaciabe.integration.transfer.response.LaboratoryResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
public class LaboratoryController {

    @Autowired
    private LaboratoryMapper mapper;

    @Autowired
    private LaboratoryService service;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/laboratory", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<LaboratoryResponse>> findAll() {
        return ResponseEntity.ok(mapper.toLaboratoryListResponse(service.findAll()));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/laboratory/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<LaboratoryResponse>> findByName(
            @RequestParam(value = "name", required = false) String name)
            throws ResourceNotFoundException, InvalidParameterException {
        if (name != null) {
            return ResponseEntity.ok(mapper.toLaboratoryListResponse(service.findByName(name)));
        }
        throw new InvalidParameterException("Name or Dni", "numeric", "Invalidate parameter request");
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/laboratory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<LaboratoryResponse> findById(
            @PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toLaboratoryResponse(service.findById(id)));
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/v1/laboratory/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<LaboratoryResponse> create(
            @RequestBody LaboratoryRequest request) {
        return ResponseEntity.ok(mapper.toLaboratoryResponse(service.save(request)));
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin(origins = "*")
    @PutMapping(path = "/v1/laboratory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<LaboratoryResponse> update(
            @PathVariable("id") long id,
            @RequestBody LaboratoryRequest request) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toLaboratoryResponse(service.update(request, id)));
    }

    @Secured("ROLE_ADMIN")
    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/v1/laboratory/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GenericResponse> delete(
            @PathVariable("id") long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.ok(new GenericResponse(200, "OK"));
    }

}
