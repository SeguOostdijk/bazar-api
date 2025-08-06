package com.proyectofinal.bazar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "Bazar API",
				version = "1.0",
				description = "Documentación de la API para la gestión de ventas de un bazar."
		)
)
@SpringBootApplication
public class BazarApplication {

	public static void main(String[] args) {
		SpringApplication.run(BazarApplication.class, args);
	}

}
