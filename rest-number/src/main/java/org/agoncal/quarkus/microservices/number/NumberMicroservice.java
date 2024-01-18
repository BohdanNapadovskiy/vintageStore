package org.agoncal.quarkus.microservices.number;


import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(title = "Number Microservic",
                description = "This microservice generates book numbers",
                version = "1.0",
                contact = @Contact(name = "Bohdan")),
        externalDocs = @ExternalDocumentation(url=""),
        tags = {
                @Tag(name = "api", description ="Public API" ),
                @Tag(name = "numbers", description = "numbers description")
        }
)
public class NumberMicroservice extends Application {
}
