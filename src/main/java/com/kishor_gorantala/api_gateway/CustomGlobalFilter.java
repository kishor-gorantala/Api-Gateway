package com.kishor_gorantala.api_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class CustomGlobalFilter implements GlobalFilter {

    private Logger logger = LoggerFactory.getLogger(CustomGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        //For getting the path of the request printed in the logs
        logger.info("Path of the request recieved -> {}", exchange.getRequest().getPath());

        // For Getting the User from where the request is coming
        String userAgent = exchange.getRequest().getHeaders().getFirst("User-Agent");

        // Log the source of the request
        if (userAgent != null) {
            if (userAgent.toLowerCase().contains("postman")) {
                System.out.println("Request received from Postman");
            } else if (userAgent.toLowerCase().contains("mozilla")) {
                System.out.println("Request received from a browser");
            } else {
                System.out.println("Request received from an unknown source: " + userAgent);
            }
        } else {
            System.out.println("No User-Agent header found");
        }

        return chain.filter(exchange);
    }
}
