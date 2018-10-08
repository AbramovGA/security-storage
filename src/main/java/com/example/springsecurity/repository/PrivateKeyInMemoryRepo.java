package com.example.springsecurity.repository;


import com.example.springsecurity.model.PrivateKey;
import com.example.springsecurity.service.KeyEnterService;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@Value
public class PrivateKeyInMemoryRepo {
    PrivateKey privateKey;

    public PrivateKeyInMemoryRepo() {
        privateKey = KeyEnterService.acceptKey();
    }
}
