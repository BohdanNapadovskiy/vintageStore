package org.agoncal.quarkus.microservices.book;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.time.Instant;

@Path("/api/books")
@Tag(name = "Book REST endpoint")
public class BookResource {

    @RestClient
    NumberProxy numberProxy;

    @Inject
    Logger logger;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Create a Book",
            description = "Creates a Book with an ISBN number")
    @Retry(maxRetries = 3,
            delay = 3000)
    @Fallback(fallbackMethod = "fallingBackOnCreatingTheBook")
    public Response createBook(@FormParam("title") String title,
                               @FormParam("author") String author,
                               @FormParam("yearOfPublication") int yearOfPublication,
                               @FormParam("genre") String genre) {
        Book book = new Book();
        book.isbn13 = numberProxy.generateIsbnNumbers().isbn13;
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        logger.info("Book created: " + book);
        return Response.status(201).entity(book).build();
    }

    public Response fallingBackOnCreatingTheBook(String title, String author, int yearOfPublication, String genre) {
        Book book = new Book();
        book.isbn13 = "Will be set later";
        book.title = title;
        book.author = author;
        book.yearOfPublication = yearOfPublication;
        book.genre = genre;
        book.creationDate = Instant.now();
        logger.info("Book saved on the disk: " + book);
        return Response.status(206).entity(book).build();
    }


}
