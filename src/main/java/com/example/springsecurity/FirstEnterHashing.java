package com.example.springsecurity;

import com.example.springsecurity.model.PrivateKey;
import com.example.springsecurity.service.AsymmetricEncryptionService;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.Scanner;


public class FirstEnterHashing {

    public static void main(String[] args) {

        System.out.println("Enter private key:");

        Scanner scanner = new Scanner(System.in);

        PrivateKey privateKey = new PrivateKey(scanner.next());

        System.out.println("private key = " + AsymmetricEncryptionService.encrypt(privateKey));

        System.out.println("salt = " + KeyGenerators.string().generateKey());

        System.out.println("\nEnter this data in resources/encryptionData/encryptionData.properties file");
    }
}
