package com.example.springsecurity.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionDataRepo {
    private String PRIVATE_KEY_HASH;
    private String SALT;

    @Value("${privateKeyHash}")
    public void setPrivateKeyHash(String privateKeyHash) {
        PRIVATE_KEY_HASH = privateKeyHash;
    }

    @Value("${salt}")
    public void setSalt(String salt) {
        SALT = salt;
    }

    public String getSalt() {
        return SALT;
    }

    public String getPrivateKeyHash() {
        return PRIVATE_KEY_HASH;
    }
}
