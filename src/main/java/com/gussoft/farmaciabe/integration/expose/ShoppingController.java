package com.gussoft.farmaciabe.integration.expose;

import com.gussoft.farmaciabe.core.business.ShoppingService;
import com.gussoft.farmaciabe.core.exception.InvalidParameterException;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.integration.mappers.ShoppingMapper;
import com.gussoft.farmaciabe.integration.transfer.request.ShoppingRequest;
import com.gussoft.farmaciabe.integration.transfer.response.GenericResponse;
import com.gussoft.farmaciabe.integration.transfer.response.ShoppingResponse;
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
public class ShoppingController {

    @Autowired
    private ShoppingMapper mapper;

    @Autowired
    private ShoppingService service;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/shopping", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<ShoppingResponse>> findAll() {
        return ResponseEntity.ok(mapper.toShoppingListResponse(service.findAll()));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/shopping/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<ShoppingResponse>> findBySeriesOrDescription(
            @RequestParam(value = "serie", required = false) String serie,
            @RequestParam(value = "dateInit", required = false) String dateInit,
            @RequestParam(value = "dateEnd", required = false) String dateEnd)
            throws ResourceNotFoundException, InvalidParameterException {
        if (serie != null) {
            return ResponseEntity.ok(mapper.toShoppingListResponse(service.findByNumberSeries(serie)));
        } else if (dateInit != null) {
            return ResponseEntity.ok(mapper.toShoppingListResponse(service.findByDateRange(dateInit, dateEnd)));
        }
        throw new InvalidParameterException("Ruc or Dni", "numeric", "Invalidate parameter request");
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/v1/shopping/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ShoppingResponse> create(
            @RequestBody ShoppingRequest request) throws ResourceNotFoundException {
        ShoppingResponse response = mapper.toShoppingResponse(service.save(request));
        if (response == null) {
            throw new ResourceNotFoundException("Record not saved!");
        }
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/v1/shopping/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ShoppingResponse> update(
            @PathVariable("id") long id,
            @RequestBody ShoppingRequest request) throws ResourceNotFoundException {
        ShoppingResponse response = mapper.toShoppingResponse(service.update(request, id));
        if (response == null) {
            throw new ResourceNotFoundException("Record not updated!");
        }
        return ResponseEntity.ok(response);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/v1/shopping/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GenericResponse> delete(
            @PathVariable("id") long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.ok(new GenericResponse(200, "OK"));
    }

}
