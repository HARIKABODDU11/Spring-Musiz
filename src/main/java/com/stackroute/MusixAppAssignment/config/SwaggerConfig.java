package com.stackroute.MusixAppAssignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

//enabling it is configuration file
@Configuration
//for application it self knows it is a config file
//we are enabling swagger
@EnableSwagger2
//enabling the swagger
public class SwaggerConfig {

    //for creating the bean
    @Bean  //creating bean
    public Docket trackApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stackroute.springboot"))
                .paths(regex("/track.*"))
                .build();
    }

}
