package org.agoncal.quarkus.microservices.book;

import jakarta.json.bind.annotation.JsonbProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.Instant;

public class IsbnNumbers {

    @JsonbProperty("isbn_13")
    public String isbn13;

    @Override
    public String toString() {
        return "IsbnNumbers{" +
                "isbn13='" + isbn13 + '\'' +
                '}';
    }
}
