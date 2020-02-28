module com.example.hexagonal.domain.model {
    requires vavr;

    exports com.example.hexagonal.domain.model to com.example.hexagonal.application, com.example.hexagonal.adapter.persistence;
}