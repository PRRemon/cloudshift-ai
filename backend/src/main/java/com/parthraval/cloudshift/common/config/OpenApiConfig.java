package com.parthraval.cloudshift.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cloudShiftOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("CloudShift AI API")
                                .description("Backend APIs for CloudShift AI Platform")
                                .version("v1.0.0")
                                .contact(
                                        new Contact()
                                                .name("Parth Raval")
                                                .email("ravalparth87@gmail.com")
                                )
                                .license(
                                        new License()
                                                .name("Apache 2.0")
                                )
                )
                .externalDocs(
                        new ExternalDocumentation()
                                .description("CloudShift AI GitHub")
                                .url("https://github.com/PRRemon/cloudshift-ai")
                );
    }

}