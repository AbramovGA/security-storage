package com.example.springsecurity.service;

import com.example.springsecurity.model.PrivateKey;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AsymmetricEncryptionService {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @SneakyThrows
    public static String encrypt(PrivateKey privateKey) {
        return encoder.encode(privateKey.getPrivateKey());
    }

    public static boolean matches(PrivateKey privateKey, String privateKeyHash) {
        return encoder.matches(privateKey.getPrivateKey(), privateKeyHash);
    }
}
