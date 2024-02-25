package ru.gb;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Библиотека - управление выдачами",
                description = "Управление выдачами книг читателям",
                version = "0.0.1-SNAPSHOT"
        )
)
public class IssuesMicroService {
    public static void main(String[] args) {
        SpringApplication.run(IssuesMicroService.class, args);
    }
}
