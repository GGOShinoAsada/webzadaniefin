package com.zadanie.zadaniefin.service;
import com.zadanie.zadaniefin.model.Author;
import com.zadanie.zadaniefin.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public interface AuthorService {
    Set<Author> getAllAuthors();
    Author getAuthorById(int id);
    void addingAuthor(Author author);
    void updatingAuthor(int auid, Author author);
    void removingAuthor(int auid);
    //Set<Book> getBooks(int auid);
}
