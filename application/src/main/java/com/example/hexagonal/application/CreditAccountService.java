package com.example.hexagonal.application;

import com.example.hexagonal.domain.model.Account;
import com.example.hexagonal.domain.model.AccountId;
import com.example.hexagonal.domain.model.Accounts;
import com.example.hexagonal.domain.model.Money;
import io.vavr.collection.Seq;
import io.vavr.control.Try;
import io.vavr.control.Validation;

import java.util.Objects;
import java.util.UUID;

import static io.vavr.API.Try;
import static io.vavr.API.Valid;

final class CreditAccountService implements CreditAccountUseCase {

    private final Accounts accounts;

    CreditAccountService(Accounts accounts) {
        Objects.requireNonNull(accounts, "accountRepository must not be null.");
        this.accounts = accounts;
    }

    @Override
    public Try<Validation<Seq<String>, UUID>> creditAccount(CreditAccountCommand command) {
        //validate non-negative amount

        return Try(() -> {
            Account account = accounts.find(AccountId.valueOf(command.getAccountId()))
                    .orElseThrow(() -> new EntityNotFoundException("Unknown account of id " + command.getAccountId()));

            account.credit(Money.dollars(command.getAmount()));

            return Valid(command.getAccountId());
        });
    }
}
