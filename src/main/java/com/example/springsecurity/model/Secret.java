package com.example.springsecurity.model;

import lombok.Value;

@Value
public class Secret {

    String privateKey;
    String secret;
    Integer id;

}
