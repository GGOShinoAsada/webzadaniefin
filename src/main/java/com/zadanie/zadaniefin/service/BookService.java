package com.zadanie.zadaniefin.service;

import com.zadanie.zadaniefin.model.Author;
import com.zadanie.zadaniefin.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public interface BookService {
    Set<Book> getAllBooks();
    Book getBookById(int id);
    void addingBook(Book b);
    void updatingBook(int bid, Book b);
    void removingBook(int bid);
    //Set<Author> getAuthors(int bid);
}
