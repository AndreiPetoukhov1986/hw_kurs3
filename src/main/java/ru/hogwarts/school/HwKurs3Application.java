package ru.hogwarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class HwKurs3Application {

    public static void main(String[] args) {
        SpringApplication.run(HwKurs3Application.class, args);
    }

}
