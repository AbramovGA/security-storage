package com.example.springsecurity.service;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class EncryptionService {

    private static final String SALT = KeyGenerators.string().generateKey();

    public static String encrypt(String password, String secret) {
        TextEncryptor passwordEncryptor = Encryptors.delux(password, SALT);
        return passwordEncryptor.encrypt(secret);
    }

    public static String decrypt(String password, String secret) {
        TextEncryptor passwordEncryptor = Encryptors.delux(password, SALT);
        return passwordEncryptor.decrypt(secret);
    }

}
