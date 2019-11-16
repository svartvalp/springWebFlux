package com.kasyan313.ReactiveWeb;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class WebHandler {
    private final  BookRepository  repository;

    public WebHandler(BookRepository repository) {
        this.repository = repository;
    }

    public Mono<ServerResponse> getBook(ServerRequest request){
        Integer bookId = Integer.valueOf(request.pathVariable("id"));
        Mono<Book> bookMono = repository.getBookById(bookId);
        return bookMono.flatMap(book -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON).body(fromObject(book)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
    public Mono<ServerResponse> getAllBooks(ServerRequest request){
        Flux<Book> bookFlux = repository.getAllBooks();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(bookFlux, Book.class);
    }
}
