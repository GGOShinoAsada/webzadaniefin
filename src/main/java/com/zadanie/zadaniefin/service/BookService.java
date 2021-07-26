package com.zadanie.zadaniefin.service;

import com.zadanie.zadaniefin.model.Author;
import com.zadanie.zadaniefin.model.Book;
import com.zadanie.zadaniefin.model.BookDTO;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public interface BookService {
    Set<BookDTO> getAllBooks();
    BookDTO getBookById(int id);
    void addingBook(BookDTO b);
    void updatingBook(BookDTO b);
    void removingBook(int bid);
}
