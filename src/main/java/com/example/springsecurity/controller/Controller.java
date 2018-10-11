package com.example.springsecurity.controller;


import com.example.springsecurity.model.PrivateKey;
import com.example.springsecurity.model.Secret;
import com.example.springsecurity.repository.EncryptionDataRepo;
import com.example.springsecurity.repository.PrivateKeyInMemoryRepo;
import com.example.springsecurity.repository.SecretRepo;
import com.example.springsecurity.service.SymmetricEncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.springsecurity.service.AuthenticationService.authenticate;

@RestController
@RequestMapping("/")
public class Controller {

    private final SecretRepo secretRepo;

    private final EncryptionDataRepo encryptionDataRepo;

    private PrivateKeyInMemoryRepo pkRepo;

    @Autowired
    public Controller(SecretRepo secretRepo, EncryptionDataRepo encryptionDataRepo, PrivateKeyInMemoryRepo pkRepo) {
        this.secretRepo = secretRepo;
        this.encryptionDataRepo = encryptionDataRepo;
        this.pkRepo = pkRepo;
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable("id") final Integer id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        ResponseEntity response;

        PrivateKey privateKey = pkRepo.getPrivateKey();

        if (authenticate(privateKey, encryptionDataRepo)) {
            response = ResponseEntity.ok()
                    .headers(headers)
                    .body(SymmetricEncryptionService.decrypt(privateKey.getPrivateKey(), secretRepo.get(id), encryptionDataRepo.getSalt()));
        } else {
            response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return response;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody final Secret secretInfo) {

        HttpStatus status;

        PrivateKey privateKey = pkRepo.getPrivateKey();

        Secret secret = new Secret(secretInfo.getSecret(), secretInfo.getId());
        if (authenticate(privateKey, encryptionDataRepo)) {
            secretRepo.put(secretInfo.getId(), SymmetricEncryptionService.encrypt(privateKey.getPrivateKey(), secret.getSecret(), encryptionDataRepo.getSalt()));
            status = HttpStatus.CREATED;
        } else {
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(status);
    }

    /*
     *If you want to enter PK with HTTP request, the code below should make the deal
     * Note: change annotation in pkRepo from @Value to @Data
     */
    //*
    @PostMapping("/create-pk")
    public ResponseEntity<Void> createPK(@RequestBody final PrivateKey privateKey) {

        HttpStatus status;

        if (authenticate(privateKey, encryptionDataRepo)) {
            pkRepo.setPrivateKey(privateKey);
            status = HttpStatus.CREATED;
        } else {
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(status);
    }
    //*/

}
