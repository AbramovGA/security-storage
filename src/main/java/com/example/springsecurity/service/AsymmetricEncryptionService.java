package com.example.springsecurity.service;

import com.example.springsecurity.model.PrivateKey;
import lombok.SneakyThrows;
import org.springframework.security.crypto.codec.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AsymmetricEncryptionService {

    @SneakyThrows
    public static String encrypt(PrivateKey privateKey) {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(
                privateKey.getPrivateKey().getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(hash));
    }
}
