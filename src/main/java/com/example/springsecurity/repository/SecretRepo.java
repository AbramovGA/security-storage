package com.example.springsecurity.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class SecretRepo {
    private Map<Integer, String> secrets = new HashMap<>();

    public void put(int id, String secret) {
        secrets.put(id, secret);
    }

    public String get(int id) {
        return secrets.get(id);
    }
}
