package com.uniCosmet.uniCosmet.configurations;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
    info = @Info(
        title = "Mi API",
        description = "Descripción detallada de mi API",
        version = "1.0.0",
        contact = @Contact(
            name = "Tu Nombre",
            email = "tuemail@dominio.com"
        )
    )
)


public class SwaggerConfig {

}
