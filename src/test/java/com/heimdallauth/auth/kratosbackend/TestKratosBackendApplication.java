package com.heimdallauth.auth.kratosbackend;

import org.springframework.boot.SpringApplication;

public class TestKratosBackendApplication {

    public static void main(String[] args) {
        SpringApplication.from(KratosBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
