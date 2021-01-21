package com.br.brangioni.java.springboot2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot2EssentialsApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Springboot2EssentialsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Springboot2EssentialsApplication.class, args);

    }

}
