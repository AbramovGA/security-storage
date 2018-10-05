package com.example.springsecurity.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:encryptionData/encryptionData.properties")
public class AppConfig {
}
