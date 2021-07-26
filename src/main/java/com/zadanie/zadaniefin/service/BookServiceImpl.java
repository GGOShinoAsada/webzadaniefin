package com.zadanie.zadaniefin.service;

import com.zadanie.zadaniefin.dao.*;
import com.zadanie.zadaniefin.model.*;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.Optional;
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    public void setDao(BookDao dao) {
        this.dao = dao;
    }
     private Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private BookDao dao;

    private AuthorDao authorDao;
    @Autowired
    public void setAuthorDao(AuthorDao dao){
        this.authorDao = dao;
    }

    @Override
    public Set<BookDTO> getAllBooks() {
        List<Book> tmp = dao.findAll();
        Set<BookDTO> books = new HashSet();
        for (Book  b : tmp){
            BookDTO dto = new BookDTO(b.getId(), b.getName(), b.getIsbn(), b.getDateofwriting(), b.getDescription());
            dto.setAuthors(b.getAuthors());
            books.add(dto);
        }
        return books;
    }

    @Override
    public BookDTO getBookById(int id) {
        BookDTO dto = new BookDTO();
        Book b = dao.findById(id).orElse(null);
        if (b!=null){
           dto = new BookDTO(b.getId(), b.getName(),b.getIsbn(), b.getDateofwriting(), b.getDescription());
           dto.setAuthors(b.getAuthors());
        }
        return dto;
    }

    @Override
    public void addingBook(BookDTO b) {
        if (b!=null){
            Book tmp = new Book(b.getName(), b.getIsbn(), b.getDateofwriting(), b.getDescription());
            tmp.setAuthors(b.getAuthors());
            dao.save(tmp);
        }
    }

    @Override
    public void updatingBook(BookDTO b) {


        if (b!=null){
            Set<Author> authors = new HashSet();
            Book tmp = dao.findById(b.getId()).orElse(null);
            if (tmp!=null){
                Set<AuthorDTO> a1 = b.getAuthorsToAdd();
                Set<AuthorDTO> a2 = b.getAuthorToRemove();
                Set<Author> au = tmp.getAuthors();
                if (a1!=null){
                    for (AuthorDTO auth : a1){
                        Author author = new Author(auth.getId(), auth.getFirstname(), auth.getSecondname(), auth.getMiddlename(), auth.getDateofBorn(), auth.getDateOfDead(), auth.getCountry(), auth.getDescription());
                        author.setBooks(auth.getBooks());
                        au.add(author);
                    }
                }

                if (a2!=null){
                    for (AuthorDTO auth : a2){
                        Author author = authorDao.findById(auth.getId()).orElse(null);
                        if (author!=null){
                            au.remove(author);
                        }
                    }
                }

                tmp.setName(b.getName());
                tmp.setIsbn(b.getIsbn());
                tmp.setDateofwriting(b.getDateofwriting());
                tmp.setDescription(b.getDescription());
                tmp.setAuthors(au);
                dao.save(tmp);
            }
        }
    }

    @Override
    public void removingBook(int bid) {
        dao.deleteById(bid);
    }
}
