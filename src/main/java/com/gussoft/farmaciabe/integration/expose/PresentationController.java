package com.gussoft.farmaciabe.integration.expose;

import com.gussoft.farmaciabe.core.business.PresentationService;
import com.gussoft.farmaciabe.core.exception.InvalidParameterException;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.integration.mappers.PresentationMapper;
import com.gussoft.farmaciabe.integration.transfer.request.PresentationRequest;
import com.gussoft.farmaciabe.integration.transfer.response.GenericResponse;
import com.gussoft.farmaciabe.integration.transfer.response.PresentationResponse;
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
public class PresentationController {

    @Autowired
    private PresentationMapper mapper;

    @Autowired
    private PresentationService service;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/presentation", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<PresentationResponse>> findAll() {
        return ResponseEntity.ok(mapper.toPresentationListResponse(service.findAll()));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/presentation/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<PresentationResponse>> findByName(
            @RequestParam(value = "name", required = false) String name)
            throws ResourceNotFoundException, InvalidParameterException {
        if (name != null) {
            return ResponseEntity.ok(mapper.toPresentationListResponse(service.findByName(name)));
        }
        throw new InvalidParameterException("Name or Dni", "numeric", "Invalidate parameter request");
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/v1/presentation/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PresentationResponse> create(
            @RequestBody PresentationRequest request) {
        return ResponseEntity.ok(mapper.toPresentationResponse(service.save(request)));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/v1/presentation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<PresentationResponse> update(
            @PathVariable("id") long id,
            @RequestBody PresentationRequest request) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toPresentationResponse(service.update(request, id)));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/v1/presentation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GenericResponse> delete(
            @PathVariable("id") long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.ok(new GenericResponse(200, "OK"));
    }

}
