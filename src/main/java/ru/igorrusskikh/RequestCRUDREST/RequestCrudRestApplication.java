package ru.igorrusskikh.RequestCRUDREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class starting Spring Boot application.
 */
@SpringBootApplication
public class RequestCrudRestApplication {

    /**
     * Private constructor for RequestCrudRestApplication class.
     */
 //   private RequestCrudRestApplication() {
 //   }

    /**
     * Main method starting Spring Boot application.
     * @param args arguments passed to main method.
     */
    public static void main(final String[] args) {
        SpringApplication.run(RequestCrudRestApplication.class, args);
    }

}
