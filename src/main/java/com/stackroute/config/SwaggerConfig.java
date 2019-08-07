package com.stackroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

//import static jdk.internal.joptsimple.util.RegexMatcher.regex;//

//import static jdk.internal.joptsimple.util.RegexMatcher.regex;
//configuartion annotation used to identify the class as configuartion
@Configuration
//enbale swagger annoatation is used to enable the swagger
@EnableSwagger2
public class SwaggerConfig {

    //bean creation for the class
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stackroute"))
                .paths(regex("/user.*"))//
                .build();
    }
}


