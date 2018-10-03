package com.example.springsecurity;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

class EncryptorProof {

    public static void main(String[] args) {

        String salt = KeyGenerators.string().generateKey();
        System.out.println(salt);
        TextEncryptor passwordEncryptor = Encryptors.delux("password", salt);

        String secret = passwordEncryptor.encrypt("secret");

        passwordEncryptor = Encryptors.delux("password", salt);

        System.out.println(passwordEncryptor.decrypt(secret));

    }

}