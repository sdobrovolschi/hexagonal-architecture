package com.example.hexagonal.adapter.rest.resource;

import com.example.hexagonal.application.CreditAccountCommand;
import com.example.hexagonal.application.CreditAccountUseCase;
import com.example.hexagonal.application.EntityNotFoundException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerErrorException;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.math.BigDecimal;
import java.util.UUID;

import static java.util.Collections.singletonMap;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.ServerResponse.noContent;
import static org.springframework.web.servlet.function.ServerResponse.notFound;
import static org.springframework.web.servlet.function.ServerResponse.unprocessableEntity;

@Component
final class CreditAccountHandler {

    private final CreditAccountUseCase creditAccountUseCase;

    CreditAccountHandler(CreditAccountUseCase creditAccountUseCase) {
        this.creditAccountUseCase = creditAccountUseCase;
    }

    ServerResponse creditAccount(ServerRequest request) throws Exception {
        UUID accountId = UUID.fromString(request.pathVariable("accountId"));
        CreditAccountRequest creditAccountRequest = request.body(CreditAccountRequest.class);

        CreditAccountCommand command = new CreditAccountCommand(accountId, creditAccountRequest.amount);

        return creditAccountUseCase.creditAccount(command)
                .map(success -> success.fold(errors -> unprocessableEntity().contentType(APPLICATION_JSON).body(singletonMap("error", errors.mkString(";"))),
                        uuid -> noContent().build()))
                .recover(EntityNotFoundException.class, failure -> notFound().build())
                .getOrElseThrow(e -> new ServerErrorException(e.getMessage()));
    }

    private static final class CreditAccountRequest {

        final BigDecimal amount;

        @JsonCreator
        CreditAccountRequest(@JsonProperty("amount") BigDecimal amount) {
            this.amount = amount;
        }
    }
}
