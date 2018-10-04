package com.example.springsecurity.service;

import com.example.springsecurity.model.PrivateKey;
import com.example.springsecurity.repository.EncryptionDataRepo;

import static com.example.springsecurity.service.AsymmetricEncryptionService.encrypt;

public class AuthorizationService {

    public static boolean authorize(PrivateKey privateKey, EncryptionDataRepo hashRepo) {
        return encrypt(privateKey).equals(hashRepo.getPrivateKeyHash());
    }

}
