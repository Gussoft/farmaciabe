package com.gussoft.farmaciabe.integration.expose;

import com.gussoft.farmaciabe.core.business.CustomerService;
import com.gussoft.farmaciabe.core.business.StorageService;
import com.gussoft.farmaciabe.core.exception.FileException;
import com.gussoft.farmaciabe.core.exception.InvalidParameterException;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Region;
import com.gussoft.farmaciabe.integration.mappers.CustomerMapper;
import com.gussoft.farmaciabe.integration.transfer.request.CustomerRequest;
import com.gussoft.farmaciabe.integration.transfer.response.CustomerResponse;
import com.gussoft.farmaciabe.integration.transfer.response.GenericResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping
public class CustomerController {

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private CustomerService service;

    @Autowired
    private StorageService storage;

    @Autowired
    private HttpServletRequest request;

    @CrossOrigin(origins = "*")
    @GetMapping(path = {"/v1/customer", "/v2/customer"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<CustomerResponse>> findByAll(
            @RequestParam(value = "dni", required = false) String dni,
            @RequestParam(value = "ruc", required = false) String ruc)
            throws ResourceNotFoundException, InvalidParameterException {
        if (dni != null) {
            return ResponseEntity.ok(mapper.toCustomerListResponse(service.findByDni(dni)));
        } else if (ruc != null) {
            return ResponseEntity.ok(mapper.toCustomerListResponse(service.findByRuc(ruc)));
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/customer/page/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Page<CustomerResponse>> findByPages(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) {
        return ResponseEntity.ok(mapper.toCustomerPageResponse(service.findAll(PageRequest.of(page, size))));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<CustomerResponse> findById(
            @PathVariable("id") Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toCustomerResponse(service.findById(id)));
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/v1/customer/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CustomerResponse> create(
            @Validated @RequestBody CustomerRequest request) {
        return ResponseEntity.ok(mapper.toCustomerResponse(service.save(request)));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/v1/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<CustomerResponse> update(
            @PathVariable("id") long id,
            @RequestBody CustomerRequest request) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toCustomerResponse(service.update(request, id)));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/v1/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GenericResponse> delete(
            @PathVariable("id") long id) throws ResourceNotFoundException, FileException {
        service.delete(id);
        return ResponseEntity.ok(new GenericResponse(200, "OK"));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/v1/customer/upload")
    public @ResponseBody
    ResponseEntity<GenericResponse> uploadPhoto(
            @RequestParam("file")MultipartFile file,
            @RequestParam("id") Long id) throws FileException, IOException, ResourceNotFoundException {
        String path = storage.store(file, "customer");
        service.uploadImage(id, path);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/v1/customer/image/")
                .path(path).toUriString();
        return ResponseEntity.ok(new GenericResponse(200, url));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/customer/image/{filename:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Resource> findByFileName(
            @PathVariable(value = "filename") String filename) throws FileException, IOException {
        Resource file = storage.loadAsResource(filename);
        String contentType = Files.probeContentType(file.getFile().toPath());
    //   HttpHeaders headers = new HttpHeaders();
    //    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                //.headers(headers)
                .body(file);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/customer/listRegions", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Region>> listRegions() {
        return ResponseEntity.ok(service.findAllRegions());
    }

}
