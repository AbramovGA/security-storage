package com.example.springsecurity.service;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class SymmetricEncryptionService {

    public static String encrypt(String password, String secret, String salt) {
        TextEncryptor passwordEncryptor = Encryptors.delux(password, salt);
        return passwordEncryptor.encrypt(secret);
    }

    public static String decrypt(String password, String secret, String salt) {
        TextEncryptor passwordEncryptor = Encryptors.delux(password, salt);
        return passwordEncryptor.decrypt(secret);
    }

}
