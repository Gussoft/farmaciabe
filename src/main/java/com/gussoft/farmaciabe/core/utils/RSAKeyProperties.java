package com.gussoft.farmaciabe.core.utils;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class RSAKeyProperties {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

    public RSAKeyProperties() {
        KeyPair pair = KeyGeneratorUtil.generateRsaKey();
        this.privateKey = (RSAPrivateKey) pair.getPrivate();
        this.publicKey = (RSAPublicKey) pair.getPublic();
    }

}
