package com.example.hexagonal.application;

import com.example.hexagonal.domain.model.Account;
import com.example.hexagonal.domain.model.AccountOpeningFailed;
import com.example.hexagonal.domain.model.Accounts;
import io.vavr.API;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import io.vavr.control.Validation;

import java.util.Objects;
import java.util.UUID;

import static com.example.hexagonal.domain.model.AccountId.newIdentity;
import static com.example.hexagonal.domain.model.Money.dollars;
import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Invalid;
import static io.vavr.API.Match;
import static io.vavr.API.Seq;
import static io.vavr.API.Valid;
import static io.vavr.Patterns.$Invalid;
import static io.vavr.Patterns.$Left;
import static io.vavr.Patterns.$Right;
import static io.vavr.Patterns.$Valid;

final class OpenAccountService implements OpenAccountUseCase {

    private final Accounts accounts;
    private final OpenAccountCommandValidator validator = new OpenAccountCommandValidator();

    OpenAccountService(Accounts accounts) {
        Objects.requireNonNull(accounts, "accountRepository must not be null.");
        this.accounts = accounts;
    }

    @Override
    public Validation<Seq<String>, UUID> openAccount(OpenAccountCommand command) {
        Validation<Seq<String>, OpenAccountCommand> result = validator.validate(command);

        return Match(result).of(
                Case($Invalid($()), API::Invalid),
                Case($Valid($()), this::doOpenAccount)
        );
    }

    private Validation<Seq<String>, UUID> doOpenAccount(OpenAccountCommand command) {
        Either<AccountOpeningFailed, Account> result = Account.open(newIdentity(), dollars(command.getAmount()));

        return Match(result).of(
                Case($Left($()), this::handleFailure),
                Case($Right($()), this::handleSuccess)
        );
    }

    private Validation<Seq<String>, UUID> handleSuccess(Account account) {
        accounts.add(account);
        return Valid(account.getId().toUUID());
    }

    private Validation<Seq<String>, UUID> handleFailure(AccountOpeningFailed accountOpeningFailed) {
        return Invalid(Seq(accountOpeningFailed.getReason()));
    }
}
