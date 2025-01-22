package com.kishor_gorantala.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(f->f.path("/currency-exchange/**")
                           .uri("lb://CURRENCY-EXCHANGE"))
                .route(f->f.path("/currency-conversion/**")
                           .uri("lb://CURRENCY-CONVERSION"))
                .build();
    }

}
