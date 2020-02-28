package com.example.hexagonal.adapter.rest.resource;

import com.example.hexagonal.application.OpenAccountCommand;
import com.example.hexagonal.application.OpenAccountUseCase;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Collections.singletonMap;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.ServerResponse.created;
import static org.springframework.web.servlet.function.ServerResponse.unprocessableEntity;

@Component
final class OpenAccountHandler {

    private final OpenAccountUseCase openAccountUseCase;

    OpenAccountHandler(OpenAccountUseCase openAccountUseCase) {
        this.openAccountUseCase = openAccountUseCase;
    }

    ServerResponse openAccount(ServerRequest request) throws Exception {
        OpenAccountRequest openAccountRequest = request.body(OpenAccountRequest.class);

        OpenAccountCommand command = new OpenAccountCommand(openAccountRequest.amount, openAccountRequest.currencyCode);
        Validation<Seq<String>, UUID> result = openAccountUseCase.openAccount(command);

        return result.fold(errors -> unprocessableEntity().contentType(APPLICATION_JSON).body(singletonMap("error", errors.mkString(";"))),
                uuid -> created(request.uriBuilder().path("/{accountId}").build(uuid)).build());
    }

    private static final class OpenAccountRequest {

        final BigDecimal amount;
        final String currencyCode;

        @JsonCreator
        OpenAccountRequest(@JsonProperty("amount") BigDecimal amount, @JsonProperty("currency_code") String currencyCode) {
            this.amount = amount;
            this.currencyCode = currencyCode;
        }
    }
}
