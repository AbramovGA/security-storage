package com.example.springsecurity.controller;


import com.example.springsecurity.model.Secret;
import com.example.springsecurity.repository.SecretRepo;
import com.example.springsecurity.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class Controller {

    private final SecretRepo secretRepo;

    @Autowired
    public Controller(SecretRepo secretRepo) {
        this.secretRepo = secretRepo;
    }


    @GetMapping("/{id}")
    public ResponseEntity<String> get(@PathVariable("id") final Integer id, @RequestParam("key") final String privateKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return ResponseEntity.ok()
                .headers(headers)
                .body(EncryptionService.decrypt(privateKey,secretRepo.get(id)));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody final Secret secret) {
        secretRepo.put(secret.getId(), EncryptionService.encrypt(secret.getPrivateKey(), secret.getSecret()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
