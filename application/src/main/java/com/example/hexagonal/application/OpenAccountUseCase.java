package com.example.hexagonal.application;

import com.example.hexagonal.domain.model.Accounts;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import java.util.UUID;

public interface OpenAccountUseCase {

    Validation<Seq<String>, UUID> openAccount(OpenAccountCommand command);

    static OpenAccountUseCase defaultService(Accounts accountRepository) {
        return new OpenAccountService(accountRepository);
    }
}
