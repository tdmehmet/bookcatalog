package com.tdmehmet.catalog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * This configuration is defined to make spring components
 * to be able to inject spring ModelMapper
 * 
 * @author Mehmet Tahir Dede
 *
 */

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
