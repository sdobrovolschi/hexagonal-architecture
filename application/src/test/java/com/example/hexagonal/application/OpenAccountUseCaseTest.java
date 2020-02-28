package com.example.hexagonal.application;

import com.example.hexagonal.domain.model.Account;
import com.example.hexagonal.domain.model.AccountId;
import com.example.hexagonal.domain.model.Accounts;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static io.vavr.API.Seq;
import static org.assertj.vavr.api.VavrAssertions.assertThat;

//Acceptance testing
class OpenAccountUseCaseTest {

    private final OpenAccountUseCase openAccountUseCase = OpenAccountUseCase.defaultService(new Accounts() {

        @Override
        public void add(Account account) {

        }

        @Override
        public Optional<Account> find(AccountId accountId) {
            return Optional.empty();
        }
    });

    @Test
    void opening() {
        Validation<Seq<String>, UUID> result = openAccountUseCase.openAccount(new OpenAccountCommand(BigDecimal.valueOf(1000), "USD"));

        assertThat(result)
                .containsValidInstanceOf(UUID.class);
    }

    @Test
    void openingFailsWhenAmountIsMissing() {
        Validation<Seq<String>, UUID> result = openAccountUseCase.openAccount(new OpenAccountCommand(null, "USD"));

        assertThat(result)
                .containsInvalid(Seq("amount must not be null."));
    }

    @Test
    void openingFailsWhenAmountIsNegative() {
        Validation<Seq<String>, UUID> result = openAccountUseCase.openAccount(new OpenAccountCommand(BigDecimal.valueOf(-100), "USD"));

        assertThat(result)
                .containsInvalid(Seq("amount must not be negative."));
    }

    @Test
    void openingFailsWhenCurrencyCodeIsMissing() {
        Validation<Seq<String>, UUID> result = openAccountUseCase.openAccount(new OpenAccountCommand(BigDecimal.valueOf(1000), null));

        assertThat(result)
                .containsInvalid(Seq("currency code must not be blank."));
    }

    @Test
    void openingFailsWhenCurrencyCodeInvalid() {
        Validation<Seq<String>, UUID> result = openAccountUseCase.openAccount(new OpenAccountCommand(BigDecimal.valueOf(1000), "asdsa"));

        assertThat(result)
                .containsInvalid(Seq("not a currency code."));
    }

    @Test
    void openingFailsWhenNonCompliantMinBalance() {
        Validation<Seq<String>, UUID> result = openAccountUseCase.openAccount(new OpenAccountCommand(BigDecimal.valueOf(100), "USD"));

        assertThat(result)
                .containsInvalid(Seq("Non-compliant minimum balance"));
    }
}
