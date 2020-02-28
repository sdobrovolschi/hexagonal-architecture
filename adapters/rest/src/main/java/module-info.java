module com.example.hexagonal.adapter.rest {
    requires com.example.hexagonal.application;
    requires spring.context;
    requires spring.web;
    requires spring.webmvc;
    requires vavr;
    requires com.fasterxml.jackson.annotation;

    exports com.example.hexagonal.adapter.rest.config;
    exports com.example.hexagonal.adapter.rest.resource;

    opens com.example.hexagonal.adapter.rest.config to spring.core;
    opens com.example.hexagonal.adapter.rest.resource to spring.core;
}