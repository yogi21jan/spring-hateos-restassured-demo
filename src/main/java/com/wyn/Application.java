package com.wyn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.RequiredArgsConstructor;

/**
 * Spring Boot launch file.
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(considerNestedRepositories = true)
@RequiredArgsConstructor
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}