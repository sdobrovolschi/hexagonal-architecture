package com.example.hexagonal.application;

import com.example.hexagonal.domain.model.Currency;
import io.vavr.Function2;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import java.math.BigDecimal;

import static io.vavr.API.Invalid;
import static io.vavr.API.Valid;
import static io.vavr.control.Validation.combine;

class OpenAccountCommandValidator {

    Validation<Seq<String>, OpenAccountCommand> validate(OpenAccountCommand command) {
        return combine(validateMoneyAmount(command.getAmount()), validateCurrencyCode(command.getCurrencyCode()))
                .ap((Function2<BigDecimal, String, OpenAccountCommand>) (bigDecimal, s) -> command);
    }

    private Validation<String, BigDecimal> validateMoneyAmount(BigDecimal amount) {
        if (amount == null) {
            return Invalid("amount must not be null.");
        }
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            return Invalid("amount must not be negative.");
        }
        return Valid(amount);
    }

    private Validation<String, String> validateCurrencyCode(String currencyCode) {
        if (currencyCode == null || currencyCode.isEmpty()) {
            return Invalid("currency code must not be blank.");
        }
        if (!Currency.fromValue(currencyCode).isPresent()) {
            return Invalid("not a currency code.");
        }
        return Valid(currencyCode);
    }
}
