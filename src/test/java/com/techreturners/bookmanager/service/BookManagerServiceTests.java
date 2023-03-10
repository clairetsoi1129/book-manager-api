package com.techreturners.bookmanager.service;

import com.techreturners.bookmanager.exception.DuplicateResourceException;
import com.techreturners.bookmanager.exception.ResourceNotFoundException;
import com.techreturners.bookmanager.model.Book;
import com.techreturners.bookmanager.model.Genre;

import com.techreturners.bookmanager.repository.BookManagerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DataJpaTest
public class BookManagerServiceTests {

    @Mock
    private BookManagerRepository mockBookManagerRepository;

    @InjectMocks
    private BookManagerServiceImpl bookManagerServiceImpl;

    @Test
    public void testGetAllBooksReturnsListOfBooks() {

        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book One", "This is the description for Book One", "Person One", Genre.Education));
        books.add(new Book(2L, "Book Two", "This is the description for Book Two", "Person Two", Genre.Education));
        books.add(new Book(3L, "Book Three", "This is the description for Book Three", "Person Three", Genre.Education));

        when(mockBookManagerRepository.findAll()).thenReturn(books);

        List<Book> actualResult = bookManagerServiceImpl.getAllBooks();

        assertThat(actualResult).hasSize(3);
        assertThat(actualResult).isEqualTo(books);

        verify(mockBookManagerRepository, times(1)).findAll();
    }

    @Test
    public void testAddABook() {

        var book = new Book(4L, "Book Four", "This is the description for Book Four", "Person Four", Genre.Fantasy);

        when(mockBookManagerRepository.save(book)).thenReturn(book);

        Book actualResult = bookManagerServiceImpl.insertBook(book);

        assertThat(actualResult).isEqualTo(book);

        verify(mockBookManagerRepository, times(1)).save(book);
    }

    @Test
    public void testAddABookFailed_WithExistingId() {

        var book = new Book(2L, "Book Two", "This is the description for Book Two", "Person Two", Genre.Fantasy);

        when(mockBookManagerRepository.existsById(book.getId())).thenReturn(true);

        Exception exception = assertThrows(DuplicateResourceException.class,
                () -> bookManagerServiceImpl.insertBook(book));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage,"Create book failed. Book with the same id: [2] already exists!");

        verify(mockBookManagerRepository, times(0)).save(book);
    }

    @Test
    public void testGetBookById() {

        Long bookId = 5L;
        var book = new Book(5L, "Book Five", "This is the description for Book Five", "Person Five", Genre.Fantasy);

        when(mockBookManagerRepository.findById(bookId)).thenReturn(Optional.of(book));

        Book actualResult = bookManagerServiceImpl.getBookById(bookId);

        assertThat(actualResult).isEqualTo(book);

        verify(mockBookManagerRepository, times(1)).findById(bookId);
    }

    @Test
    public void testGetBookByIdFailed_WithNotExistId() {

        Long bookId = 5L;

        when(mockBookManagerRepository.findById(bookId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class,
                () -> bookManagerServiceImpl.getBookById(bookId));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, "Book with book id: [5] does not exist!");

        verify(mockBookManagerRepository, times(1)).findById(bookId);
    }

    //User Story 4 - Update Book By Id Solution
    @Test
    public void testUpdateBookById() {

        Long bookId = 5L;
        var book = new Book(5L, "Book Five", "This is the description for Book Five", "Person Five", Genre.Fantasy);

        when(mockBookManagerRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(mockBookManagerRepository.save(book)).thenReturn(book);

        bookManagerServiceImpl.updateBookById(bookId, book);

        verify(mockBookManagerRepository, times(1)).save(book);
    }

    @Test
    public void testUpdateBookByIdFailed_WithNotExistId() {

        Long bookId = 5L;
        var book = new Book(5L, "Book Five", "This is the description for Book Five", "Person Five", Genre.Fantasy);

        when(mockBookManagerRepository.findById(bookId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class,
                () -> bookManagerServiceImpl.updateBookById(bookId, book));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, "Book with book id: [5] does not exist!");

        verify(mockBookManagerRepository, times(0)).save(book);
    }

    @Test
    public void testDeleteBookById() {

        Long bookId = 1L;

        when(mockBookManagerRepository.existsById(bookId)).thenReturn(true);

        bookManagerServiceImpl.deleteBookById(bookId);

        verify(mockBookManagerRepository, times(1)).existsById(bookId);
        verify(mockBookManagerRepository, times(1)).deleteById(bookId);
    }

    @Test
    public void testDeleteBookByIdFailed_WithNotExistId() {

        Long bookId = 5L;

        when(mockBookManagerRepository.existsById(bookId)).thenReturn(false);

        Exception exception = assertThrows(ResourceNotFoundException.class,
                () -> bookManagerServiceImpl.deleteBookById(bookId));

        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, "Book with book id: [5] does not exist!");

        verify(mockBookManagerRepository, times(1)).existsById(bookId);
        verify(mockBookManagerRepository, times(0)).deleteById(bookId);
    }

}
