package com.gussoft.farmaciabe.core.business.impl;

import com.gussoft.farmaciabe.core.business.AuthenticationService;
import com.gussoft.farmaciabe.core.models.Role;
import com.gussoft.farmaciabe.core.models.Users;
import com.gussoft.farmaciabe.core.repository.RoleRepository;
import com.gussoft.farmaciabe.core.repository.UsersRepository;
import com.gussoft.farmaciabe.integration.transfer.request.UsersRequest;
import com.gussoft.farmaciabe.integration.transfer.response.LoginResponse;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenServiceImpl tokenService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public Users registerUser(UsersRequest request) {
        Optional<Role> role = roleRepository.findByAuthority("USER");
        Set<Role> authorities = new HashSet<>();
        authorities.add(role.get());
        Users user = Users.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .state("true")
                .subsidiary("local")
                .authorities(authorities).build();
        return repository.save(user);
    }

    @Override
    public LoginResponse loginUser(String username, String password) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);
            Users user = repository.findByUser(username);
            if (user != null) {
                return new LoginResponse(user, token);
            }
        } catch (AuthenticationException e) {
            return new LoginResponse(null, "");
        }
        return null;
    }

    public void logout(String token) {
        Jwt jwt = tokenService.decoderToken(token);

    }
}
