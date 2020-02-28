package com.example.hexagonal.adapter.rest.resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RequestPredicates.accept;
import static org.springframework.web.servlet.function.RouterFunctions.route;

@Configuration
class AccountResource {

    @Bean
    RouterFunction<ServerResponse> openAccountRoute(OpenAccountHandler handler) {
        return route()
                .POST(
                        "/accounts",
                        accept(APPLICATION_JSON),
                        handler::openAccount
                )
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> creditAccountRoute(CreditAccountHandler handler) {
        return route()
                .PATCH(
                        "/accounts/{accountId}",
                        accept(APPLICATION_JSON),
                        handler::creditAccount
                )
                .build();
    }
}
