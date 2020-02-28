package com.example.hexagonal.application;

import com.example.hexagonal.domain.model.Accounts;
import io.vavr.collection.Seq;
import io.vavr.control.Try;
import io.vavr.control.Validation;

import java.util.UUID;

public interface CreditAccountUseCase {

    Try<Validation<Seq<String>, UUID>> creditAccount(CreditAccountCommand command);

    static CreditAccountUseCase defaultService(Accounts accounts) {
        return new CreditAccountService(accounts);
    }
}
