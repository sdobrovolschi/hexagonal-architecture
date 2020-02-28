package com.example.hexagonal.service;

import com.example.hexagonal.adapter.persistence.InMemoryAccountRepository;
import com.example.hexagonal.adapter.rest.config.RestConfig;
import com.example.hexagonal.application.CreditAccountUseCase;
import com.example.hexagonal.application.OpenAccountUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RestConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Configuration
	class Config {

    	@Bean
		InMemoryAccountRepository accountRepository() {
    		return new InMemoryAccountRepository();
		}

		@Bean
		OpenAccountUseCase openAccountUseCase(InMemoryAccountRepository accountRepository) {
			return OpenAccountUseCase.defaultService(accountRepository);
		}

		@Bean
		CreditAccountUseCase creditAccountUseCase(InMemoryAccountRepository accountRepository) {
    		return CreditAccountUseCase.defaultService(accountRepository);
		}
	}
}
