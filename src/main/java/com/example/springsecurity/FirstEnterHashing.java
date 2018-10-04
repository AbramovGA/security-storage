package com.example.springsecurity;

import com.example.springsecurity.model.PrivateKey;
import com.example.springsecurity.service.AsymmetricEncryptionService;
import org.springframework.security.crypto.keygen.KeyGenerators;


public class FirstEnterHashing {

    public static void main(String[] args) {

        System.out.println("private key = " + AsymmetricEncryptionService.encrypt(new PrivateKey("password")));

        System.out.println("salt = " + KeyGenerators.string().generateKey());
    }
}
