package com.example.springsecurity.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.example.springsecurity")
@PropertySource("classpath:encryptionData/encryptionData.properties")
public class AppConfig {
}
