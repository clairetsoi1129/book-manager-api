package com.techreturners.bookmanager.config;

import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.model.Genre;
import com.techreturners.bookmanager.repository.BookManagerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner loadInitialData(BookManagerRepository bookManagerRepository){
        return args -> {
            Book book1 = new Book(1L, "Book One", "This is the description for Book One", "Person One", Genre.Education);
            Book book2 = new Book(2L, "Book Two", "This is the description for Book Two", "Person Two", Genre.Education);

            bookManagerRepository.saveAll(List.of(book1, book2));
        };
    }
}
