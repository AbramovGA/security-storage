package com.example.springsecurity.service;

import com.example.springsecurity.config.ApplicationContextProvider;
import com.example.springsecurity.model.PrivateKey;
import com.example.springsecurity.repository.EncryptionDataRepo;
import org.springframework.context.ApplicationContext;

import java.io.Console;

public class KeyEnterService {

    public static String readKey() {
        Console console = System.console();
        char[] pwd = console.readPassword("Enter the master-password: ");
        return String.valueOf(pwd);
    }

    public static PrivateKey acceptKey() {

        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
        EncryptionDataRepo encryptionDataRepo = ctx.getBean(EncryptionDataRepo.class);

        String rawKey;
        PrivateKey key;

        do {
            rawKey = readKey();
            key = new PrivateKey(rawKey);
        } while (!matches(key, encryptionDataRepo.getPrivateKeyHash()));

        return key;
    }

    private static boolean matches(PrivateKey pk, String pkHash) {
        boolean result;

        if (!AsymmetricEncryptionService.matches(pk, pkHash)) {
            System.out.println("The key doesn't matches hash.");
            result = false;
        } else {
            result = true;
        }
        return result;
    }

}
