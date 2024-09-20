package com.example.dostavista.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Dostavista",
                description = "Courier System", version = "1.0.0",
                contact = @Contact(
                        name = "Irvanev Vitaly",
                        email = "irvvanevv@mail.ru"
                )
        )
)
public class OpenApiConfig {

}
