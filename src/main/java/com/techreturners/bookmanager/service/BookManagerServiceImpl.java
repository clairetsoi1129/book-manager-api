package com.techreturners.bookmanager.service;

import com.techreturners.bookmanager.exception.DuplicateResourceException;
import com.techreturners.bookmanager.exception.ResourceNotFoundException;
import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.repository.BookManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookManagerServiceImpl implements BookManagerService {
    private final String ERROR_DUPLICATE_BOOK_ID = "Create book failed. Book with the same id: [%s] alreay exists!";
    private final String ERROR_BOOK_NOT_EXISTS = "Book with book id: [%s] does not exist!";

    @Autowired
    BookManagerRepository bookManagerRepository;

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        bookManagerRepository.findAll().forEach(books::add);
        return books;
    }

    @Override
    public Book insertBook(Book book) {
        boolean exist = bookManagerRepository.existsById(book.getId());
        if (exist)
            throw new DuplicateResourceException(ERROR_DUPLICATE_BOOK_ID.formatted(book.getId()));
        return bookManagerRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> bookOptional = bookManagerRepository.findById(id);
        if (bookOptional.isEmpty())
            throw new ResourceNotFoundException(ERROR_BOOK_NOT_EXISTS.formatted(id));
        return bookOptional.get();
    }

    //User Story 4 - Update Book By Id Solution
    @Override
    public void updateBookById(Long id, Book book) {
        Optional<Book> optionalBook = bookManagerRepository.findById(id);
        if (optionalBook.isEmpty())
            throw new ResourceNotFoundException(ERROR_BOOK_NOT_EXISTS.formatted(id));

        Book retrievedBook = optionalBook.get();
        retrievedBook.setTitle(book.getTitle());
        retrievedBook.setDescription(book.getDescription());
        retrievedBook.setAuthor(book.getAuthor());
        retrievedBook.setGenre(book.getGenre());

        bookManagerRepository.save(retrievedBook);
    }

    @Override
    public void deleteBookById(Long id) {
        boolean exist = bookManagerRepository.existsById(id);
        if (!exist)
            throw new ResourceNotFoundException(ERROR_BOOK_NOT_EXISTS.formatted(id));
        bookManagerRepository.deleteById(id);
    }

}
