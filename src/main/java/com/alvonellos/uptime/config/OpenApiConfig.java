package com.alvonellos.uptime.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(
            @Value("${api.description}") String appDescription,
            @Value("${api.version}") String apiVersion,
            @Value("${api.name}") String appName) {
        return new OpenAPI()
                .info(
                        new Info()
                                .title(appName)
                                .version(apiVersion)
                                .description(appDescription)
                                .termsOfService("http://swagger.io/terms/")
                                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
