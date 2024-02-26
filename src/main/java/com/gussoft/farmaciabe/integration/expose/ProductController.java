package com.gussoft.farmaciabe.integration.expose;

import com.gussoft.farmaciabe.core.business.ProductService;
import com.gussoft.farmaciabe.core.exception.InvalidParameterException;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.integration.mappers.ProductMapper;
import com.gussoft.farmaciabe.integration.transfer.request.ProductRequest;
import com.gussoft.farmaciabe.integration.transfer.response.GenericResponse;
import com.gussoft.farmaciabe.integration.transfer.response.ProductResponse;
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
public class ProductController {

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductService service;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(mapper.toProductListResponse(service.findAll()));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/product/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<ProductResponse>> findByNameOrDescription(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description)
            throws ResourceNotFoundException, InvalidParameterException {
        if (name != null) {
            return ResponseEntity.ok(mapper.toProductListResponse(service.findByName(name)));
        } else if (description != null) {
            return ResponseEntity.ok(mapper.toProductListResponse(service.findByDescription(description)));
        }
        throw new InvalidParameterException("Ruc or Dni", "numeric", "Invalidate parameter request");
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/v1/product/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ProductResponse> create(
            @RequestBody ProductRequest request) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toProductResponse(service.save(request)));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/v1/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<ProductResponse> update(
            @PathVariable("id") long id,
            @RequestBody ProductRequest request) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toProductResponse(service.update(request, id)));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/v1/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GenericResponse> delete(
            @PathVariable("id") long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.ok(new GenericResponse(200, "OK"));
    }

}
