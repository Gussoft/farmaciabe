package com.gussoft.farmaciabe.core.business.impl;

import static com.gussoft.farmaciabe.core.utils.Constrains.MESSAGE_NOT_FOUND;

import com.gussoft.farmaciabe.core.business.UserService;
import com.gussoft.farmaciabe.core.exception.ResourceNotFoundException;
import com.gussoft.farmaciabe.core.models.Employee;
import com.gussoft.farmaciabe.core.models.Users;
import com.gussoft.farmaciabe.core.repository.UsersRepository;
import com.gussoft.farmaciabe.integration.transfer.request.UsersRequest;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UsersRepository repository;

    @Override
    public List<Users> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Users> findByNik(String nik) throws ResourceNotFoundException {
        List<Users> users = repository.findByUsername(nik);
        if (users == null) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Nik", nik));
        }
        return users;
    }

    @Override
    public List<Users> findByEmail(String email) throws ResourceNotFoundException {
        List<Users> users = null; //repository.findByEmail(email);
        if (users == null) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Email", email));
        }
        return users;
    }

    @Override
    public Users save(UsersRequest request) {
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setSubsidiary(request.getSubsidiary());
        user.setState(request.getState());

        return repository.save(user);
    }

    @Override
    public Users update(UsersRequest request, Long id) throws ResourceNotFoundException {
        Optional<Users> data = repository.findById(id);
        if (!data.isPresent()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        Users user = data.get();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setSubsidiary(request.getSubsidiary());
        user.setState(request.getState());
        return repository.save(user);
    }

    @Override
    public void addEmployeeUser(Long id, Employee employee) throws ResourceNotFoundException {
        Optional<Users> data = repository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        Users users = data.get();
        users.setEmployee(employee);
        repository.save(users);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Users> data = repository.findById(id);
        if (data.isEmpty()) {
            throw new ResourceNotFoundException(MessageFormat.format(MESSAGE_NOT_FOUND, "Id", id));
        }
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUser(username);
    }

}
