//module com.example.hexagonal.service {
//    requires com.example.hexagonal.adapter.rest;
//    requires com.example.hexagonal.adapter.persistence;
//    requires com.example.hexagonal.domain.model;
//    requires com.example.hexagonal.application;
//
//    requires spring.context;
//    requires spring.boot;
//    requires spring.boot.autoconfigure;
//    requires com.fasterxml.classmate;
//
//    exports com.example.hexagonal.service;
//
//    opens com.example.hexagonal.service to spring.core;
//}