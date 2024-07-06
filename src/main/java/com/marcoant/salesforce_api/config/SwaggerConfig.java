package com.marcoant.salesforce_api.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    GroupedOpenApi vendedorApi() {
        return GroupedOpenApi.builder().group("vendedor").pathsToMatch("/vendedor/**").build();
    }
}
