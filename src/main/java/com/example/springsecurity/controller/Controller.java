package com.example.springsecurity.controller;


import com.example.springsecurity.model.PrivateKey;
import com.example.springsecurity.model.Secret;
import com.example.springsecurity.model.SecretWithPK;
import com.example.springsecurity.repository.EncryptionDataRepo;
import com.example.springsecurity.repository.SecretRepo;
import com.example.springsecurity.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.springsecurity.service.AuthorizationService.authorize;

@RestController
@RequestMapping("/")
public class Controller {

    private final SecretRepo secretRepo;

    private final EncryptionDataRepo encryptionDataRepo;


    @Autowired
    public Controller(SecretRepo secretRepo, EncryptionDataRepo encryptionDataRepo) {
        this.secretRepo = secretRepo;
        this.encryptionDataRepo = encryptionDataRepo;
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable("id") final Integer id, @RequestParam("key") final PrivateKey privateKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        ResponseEntity response;

        if (authorize(privateKey, encryptionDataRepo)) {
            response = ResponseEntity.ok()
                    .headers(headers)
                    .body(EncryptionService.decrypt(privateKey.getPrivateKey(), secretRepo.get(id), encryptionDataRepo.getSalt()));
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody final SecretWithPK secretInfo) {

        HttpStatus status;

        PrivateKey privateKey = new PrivateKey(secretInfo.getPrivateKey());
        Secret secret = new Secret(secretInfo.getSecret(), secretInfo.getId());
        if (authorize(privateKey, encryptionDataRepo)) {
            secretRepo.put(secretInfo.getId(), EncryptionService.encrypt(privateKey.getPrivateKey(), secret.getSecret(), encryptionDataRepo.getSalt()));
            status = HttpStatus.CREATED;
        } else {
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(status);
    }
}
