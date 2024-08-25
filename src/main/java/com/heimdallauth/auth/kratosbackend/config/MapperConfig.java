package com.heimdallauth.auth.kratosbackend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper userModelMapper(){
        ModelMapper userModelMapper = new ModelMapper();
        return userModelMapper;
    }
}
