package com.example.springsecurity.repository;


import com.example.springsecurity.model.PrivateKey;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PrivateKeyInMemoryRepo {
    PrivateKey privateKey = new PrivateKey("default");
}
