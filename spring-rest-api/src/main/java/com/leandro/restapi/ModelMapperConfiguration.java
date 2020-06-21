package com.leandro.restapi;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ModelMapperConfiguration {
  @Bean
  public ModelMapper getModelMapper() {
    return new ModelMapper();
  }

}