package com.kasyan313.ReactiveWeb;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class MainRepository implements BookRepository {
    List<Book> books;
    public MainRepository() {
        books = new ArrayList<>();
        books.add(new Book(1, "kosima", "author"));
        books.add(new Book(2, "ffff", "gfggf"));
    }

    @Override
    public Mono<Book> getBookById(int id) {
        for(Book book : books) {
            if (book.getId() == id){
                return Mono.just(book);
            }
        }
        return Mono.empty();
    }

    @Override
    public Flux<Book> getAllBooks() {
        if (books.size() == 0)
            return Flux.empty();
        return Flux.fromIterable(books);
    }

    @Override
    public void deleteBook(int id) {

    }
}
