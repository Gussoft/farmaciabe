package com.gussoft.farmaciabe.integration.expose;

import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;

import com.gussoft.farmaciabe.core.business.UserService;
import com.gussoft.farmaciabe.core.exception.InvalidParameterException;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Employee;
import com.gussoft.farmaciabe.core.repository.EmployeeRepository;
import com.gussoft.farmaciabe.integration.mappers.UsersMapper;
import com.gussoft.farmaciabe.integration.transfer.request.UsersRequest;
import com.gussoft.farmaciabe.integration.transfer.response.GenericResponse;
import com.gussoft.farmaciabe.integration.transfer.response.UsersResponse;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
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
public class UsersController {

    @Autowired
    private UsersMapper mapper;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserService service;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<UsersResponse>> findAll() {
        return ResponseEntity.ok(mapper.toUsersListResponse(service.findAll()));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/v1/users/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<UsersResponse>> findByNikOrEmail(
            @RequestParam(value = "nik", required = false) String nik,
            @RequestParam(value = "email", required = false) String email)
            throws ResourceNotFoundException, InvalidParameterException {
        if (nik != null) {
            return ResponseEntity.ok(mapper.toUsersListResponse(service.findByNik(nik)));
        } else if (email != null) {
            return ResponseEntity.ok(mapper.toUsersListResponse(service.findByEmail(email)));
        }
        throw new InvalidParameterException("Nik or Email", "numeric", "Invalidate parameter request");
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/v1/users/", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<UsersResponse> create(
            @RequestBody UsersRequest request) {
        return ResponseEntity.ok(mapper.toUsersResponse(service.save(request)));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/v1/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<UsersResponse> update(
            @PathVariable("id") long id,
            @RequestBody UsersRequest request) throws ResourceNotFoundException {
        return ResponseEntity.ok(mapper.toUsersResponse(service.update(request, id)));
    }

    @CrossOrigin(origins = "*")
    @PutMapping(path = "/v1/users/{id}/employee/{idEmploy}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GenericResponse> updateAddEmployee(
            @PathVariable(value = "id", required = true) long id,
            @PathVariable(value = "idEmploy", required = true) long idEmploy) throws ResourceNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(idEmploy);
        if (employee.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", idEmploy));
        }
        service.addEmployeeUser(id, employee.get());
        return ResponseEntity.ok(new GenericResponse(200, "OK"));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/v1/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<GenericResponse> delete(
            @PathVariable("id") long id) throws ResourceNotFoundException {
        service.delete(id);
        return ResponseEntity.ok(new GenericResponse(200, "OK"));
    }

}
