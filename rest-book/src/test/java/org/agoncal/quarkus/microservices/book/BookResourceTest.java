package org.agoncal.quarkus.microservices.book;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.FormParam;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;

@QuarkusTest
class BookResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
                .formParam("title","Some title")
                .formParam("author", "Some author")
                .formParam("yearOfPublication", 2017)
                .formParam("genre", "IT")
          .when()
                .post("/api/books")
          .then()
             .statusCode(201)
                .body("isbn_13", startsWith("13-"))
                .body("title", is("Some title"))
                .body("author", is("Some author"))
                .body("year_of_publication", is(2017))
                .body("genre", is("IT"));
    }

}