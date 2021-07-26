package com.zadanie.zadaniefin.service;
import com.zadanie.zadaniefin.model.Author;
import com.zadanie.zadaniefin.model.AuthorDTO;
import com.zadanie.zadaniefin.model.Book;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public interface AuthorService {
    Set<AuthorDTO> getAllAuthors();
    AuthorDTO getAuthorById(int id);
    void addingAuthor(AuthorDTO author);
    void updatingAuthor(AuthorDTO author);
    void removingAuthor(int auid);
}
