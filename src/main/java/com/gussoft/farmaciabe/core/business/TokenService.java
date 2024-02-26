package com.gussoft.farmaciabe.core.business;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

public interface TokenService {

    String generateJwt(Authentication auth);

    Jwt decoderToken(String token);

}
