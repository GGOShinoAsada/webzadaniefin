package com.zadanie.zadaniefin.service;

import com.zadanie.zadaniefin.dao.*;
import com.zadanie.zadaniefin.model.*;
import org.apache.commons.collections4.IteratorUtils;
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
    }

    @Override
    public void removingBook(int bid) {
        if (bid>=0)
            dao.deleteById(bid);
    }
    /*@Override
    public Set<Book> getAllBooks() {
        return new HashSet(dao.findAll());
    }

    @Override
    public Book getBookById(int id) {
       return dao.findById(id).orElse(null);
        // return Optional.ofNullable(dao.getBookById(id)).orElse(null);
    }

    @Override
    public void addingBook(Book b) {
        if (b!=null){
            dao.save(b);
        }
        //dao.save(b);
    }

    @Override
    public void updatingBook(int bid, Book b) {
        if (bid>=0){
            Book book = dao.findById(bid).orElse(null);//Optional.ofNullable(dao.getBookById(bid)).orElse(null);
            if (book!=null){
                book.setIsbn(b.getIsbn());
                book.setName(b.getName());
                book.setDescription(b.getDescription());
                book.setDateofwriting(b.getDateofwriting());
                book.setAuthors(b.getAuthors());
                dao.save(book);
            }
        }
    }

    @Override
    public void removingBook(int bid) {
        dao.deleteById(bid);
    }

    @Override
    public Set<Author> getAuthors(int bid) {
        Set<Author> authors = dao.getBooksForAuthor(bid);
        return authors;
    }*/
    /*private BookDao dao;
    @Override
    public Set<Book> getAllBooks() {
        Set<Book> books = new HashSet();
        books = new HashSet(IteratorUtils.toList(Optional.ofNullable(dao.getAllBooks().iterator()).orElse(null)));
         return  books;
    }

    @Override
    public Book getBookById(int id) {
        Book book = new Book();
        book = Optional.ofNullable(dao.getBookById(id)).orElse(null);
        return book;
    }

    @Override
    public void addingBook(Book b) {
        if (b!=null){
            dao.addingBook(b);
        }
    }

    @Override
    public void updatingBook(int bid, Book b) {
        Book data = Optional.ofNullable(dao.getBookById(bid)).orElse(null);
        if (data!=null){
            data.setIsbn(b.getIsbn());
            data.setAuthors(b.getAuthors());
            data.setDateofwriting(b.getDateofwriting());
            data.setDescription(b.getDescription());
            data.setName(b.getName());
           // dao.save(data);
        }
    }

    @Override
    public void removingBook(int bid) {
        Book b = Optional.ofNullable(dao.getBookById(bid)).orElse(null);
        if (b!=null){
            dao.removingBook(b.getId());
        }
    }*/
}
