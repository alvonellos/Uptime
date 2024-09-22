package com.alvonellos.uptime.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //Look, I know I could do this in a @WebMvcConfigurer bean,
    //but I just now learned how to do that, and I think this is more
    //the convention to have it all in one nice place dedicated to its
    //purpose.

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //#!@#!@# really helpful reference for what
        //this is doing here because baeldung is absolutely
        //the hero of every spring boot developer on planet earth.
        //https://www.baeldung.com/spring-cors
        registry.addMapping("/**")
             .allowedOrigins("*")
             .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
             .allowedHeaders("*")
             //.allowCredentials(true)
             .maxAge(3600);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //For static resources, serve everything from the static folder
        //if it's not matched with any file, then serve the index.html file
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);
                        return requestedResource.exists() && requestedResource.isReadable() ?
                                requestedResource : new ClassPathResource("/static/index.html");
                    }
                });

        //For webjars
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/");
    }
}