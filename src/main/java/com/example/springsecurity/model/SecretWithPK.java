package com.example.springsecurity.model;

import lombok.Value;

@Value
public class SecretWithPK {
    String privateKey;
    String secret;
    Integer id;
}
