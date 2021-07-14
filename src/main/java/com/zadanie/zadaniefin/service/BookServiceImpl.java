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

    @Override
    public Set<Book> getAllBooks() {
        Set<Book> tmp = new HashSet(dao.findAll());
        return tmp;
    }

    @Override
    public Book getBookById(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void addingBook(Book b) {
        if (b!=null){
            dao.save(b);
        }
        else {
            logger.error("book is null");
        }
    }

    @Override
    public void updatingBook(int bid, Book b) {
        if (bid>=0 && b!=null){
            Book book = dao.findById(bid).orElse(null);
            if (book!=null){
                book.setIsbn(b.getIsbn());
                book.setName(b.getName());
                book.setDescription(b.getDescription());
                book.setDateofwriting(b.getDateofwriting());
                book.setAuthors(b.getAuthors());
                dao.save(book);
            }
        }
        else {
            logger.error("please check input values");
        }
    }

    @Override
    public void removingBook(int bid) {
        if (bid>=0)
            dao.deleteById(bid);
        else
            logger.error("please check input values");
    }
}
