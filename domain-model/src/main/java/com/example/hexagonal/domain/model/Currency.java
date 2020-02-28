package com.example.hexagonal.domain.model;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public enum Currency {

    USD, EUR;

    private static final Map<String, Currency> STRING_TO_ENUM = Stream.of(values())
            .collect(toMap(Enum::name, identity()));

    public static Optional<Currency> fromValue(String currencyCode) {
        return Optional.ofNullable(STRING_TO_ENUM.get(currencyCode));
    }
}
