package com.alvonellos.uptime.config;

import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@Log
public class FaviconConfiguration {
 
    @Bean
    public SimpleUrlHandlerMapping customFaviconHandlerMapping() {
        log.entering(this.getClass().getName(), "customFaviconHandlerMapping");

        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();

        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap(
          "/favicon.ico", faviconRequestHandler()));

        log.exiting(this.getClass().getName(), "customFaviconHandlerMapping");

        return mapping;
    }

    @Bean
    protected ResourceHttpRequestHandler faviconRequestHandler() {
        log.entering(this.getClass().getName(), "faviconRequestHandler");

        ResourceHttpRequestHandler requestHandler
          = new ResourceHttpRequestHandler();
        ClassPathResource classPathResource
          = new ClassPathResource("static/assets/img/favicon.ico"); //todo update


        List<Resource> locations = List.of(classPathResource);
        requestHandler.setLocations(locations);

        log.exiting(this.getClass().getName(), "faviconRequestHandler");

        return requestHandler;
    }
}