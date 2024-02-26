package com.gussoft.farmaciabe.core.business.impl;

import com.gussoft.farmaciabe.core.business.TokenService;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JwtEncoder encoder;

    @Autowired
    private JwtDecoder decoder;

    @Override
    public String generateJwt(Authentication auth) {
        Instant now = Instant.now();

        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles", scope)
                .audience(List.of("extra", "info"))
                .build();
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Override
    public Jwt decoderToken(String token) {
        return decoder.decode(token);
    }

}
