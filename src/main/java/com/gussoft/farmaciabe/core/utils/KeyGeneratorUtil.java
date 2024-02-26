package com.gussoft.farmaciabe.core.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class KeyGeneratorUtil {

    public static KeyPair generateRsaKey() {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
            keyGenerator.initialize(2048);
            keyPair = keyGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return keyPair;
    }

}
