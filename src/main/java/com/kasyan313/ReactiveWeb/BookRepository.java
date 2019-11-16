package com.kasyan313.ReactiveWeb;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface BookRepository {
    public Mono<Book> getBookById(int id);
    public Flux<Book> getAllBooks();
    public void deleteBook(int id);
}
