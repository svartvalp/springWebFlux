package com.kasyan313.ReactiveWeb;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class AppConfiguration {
    @Bean
    public BookRepository repository() {
        return new MainRepository();
    }

    @Bean
    public WebHandler handler(BookRepository repository) {
        return new WebHandler(repository);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(WebHandler handler) {
        return nest(path("/book"), nest(accept(APPLICATION_JSON), route(GET("/{id}"), handler::getBook)
                .andRoute(method(HttpMethod.GET), handler::getAllBooks)));
    }
}
