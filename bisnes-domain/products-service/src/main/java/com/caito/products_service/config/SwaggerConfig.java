package com.caito.products_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Value("${application.version}")
    private String version;

    @Bean
    public GroupedOpenApi api(){
        return GroupedOpenApi.builder()
                .group("products")
                .packagesToScan("com.caito.products_service")
                .build();
    }

    @Bean
    public OpenAPI springProductOA(){
        return new OpenAPI()
                .info(new Info().title("Servicio de productos")
                        .description("Servicio registro productos microservices")
                        .version(version)
                        .contact(new Contact().name("Caito").email(
                                "caitocd@gmail.com")));
    }
}
