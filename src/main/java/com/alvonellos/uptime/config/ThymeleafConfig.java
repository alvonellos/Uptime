package com.alvonellos.uptime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.nio.charset.StandardCharsets;

@Configuration
public class ThymeleafConfig {

    @Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver htmlTemplateResolver = new SpringResourceTemplateResolver();
        htmlTemplateResolver.setPrefix("classpath:/templates/");
        htmlTemplateResolver.setSuffix(".html");
        htmlTemplateResolver.setTemplateMode("HTML5");
        htmlTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return htmlTemplateResolver;
    }
}