package com.alvonellos.uptime.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNullApi;

@Configuration
public class StaticContentConfiguration implements WebFilter {

    private static final String INDEX_FILE_PATH = "classpath:static/index.html";

    {

    }
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (pathMatchesWebAppResource(path)) {
            redirectToWebApp(exchange, chain);
        } else {
            chain.filter(exchange);
        }
        return null;
    }

    private void redirectToWebApp(ServerWebExchange exchange, WebFilterChain chain) {
        chain.filter(exchange.mutate()
                .request(exchange.getRequest().mutate().path(INDEX_FILE_PATH).build())
                .build());
    }

    private boolean pathMatchesWebAppResource(String path) {
        return !path.startsWith("/api") && path.matches("[^\\\\.]*");
    }
}
