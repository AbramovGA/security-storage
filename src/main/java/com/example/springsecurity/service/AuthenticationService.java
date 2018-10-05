package com.example.springsecurity.service;

import com.example.springsecurity.model.PrivateKey;
import com.example.springsecurity.repository.EncryptionDataRepo;

import static com.example.springsecurity.service.AsymmetricEncryptionService.matches;

public class AuthenticationService {

    public static boolean authenticate(PrivateKey privateKey, EncryptionDataRepo hashRepo) {
        return matches(privateKey, hashRepo.getPrivateKeyHash());
    }

}
