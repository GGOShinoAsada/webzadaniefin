package com.zadanie.zadaniefin.service;

import com.zadanie.zadaniefin.dao.*;
import com.zadanie.zadaniefin.model.*;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;
import java.util.Optional;
import com.zadanie.zadaniefin.config.*;
@Service
public class AuthorServiceImpl implements AuthorService {

    private Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private AuthorDao dao;
    @Autowired
    public void setDao(AuthorDao dao) {
        this.dao = dao;
    }

    @Override
    public Set<Author> getAllAuthors() {
        Set<Author> tmp = new HashSet(dao.findAll());
        return tmp;
    }

    @Override
    public Author getAuthorById(int id) {
        Author a = new Author();
        a = dao.findById(id).orElse(null);
        return a;
    }

    @Override
    public void addingAuthor(Author author) {
        if (author!=null){
            int d = 0;
            dao.save(author);
        }
        else {
            logger.error("author is null");
        }
    }

    @Override
    public void updatingAuthor(int auid, Author author) {
        if (auid>=0 && author!=null)
        {
            Author data = dao.findById(auid).orElse(null);
            if (data!=null){
                data.setBooks(author.getBooks());
                data.setFirstname(author.getFirstname());
                data.setSecondname(author.getSecondname());
                data.setMiddlename(author.getMiddlename());
                data.setDateofborn(author.getDateofborn());
                data.setDateofdead(author.getDateofdead());
                data.setDescription(author.getDescription());
                dao.save(data);
            }
        }
        else {
            logger.error("please check input values");
        }
    }

    @Override
    public void removingAuthor(int auid) {
        if (auid>=0)
            dao.deleteById(auid);
        else
            logger.error("please check input values");
    }

    /*@Override
    public Set<Author> getAllAuthors() {
        return new HashSet(dao.findAll());
    }

    @Override
    public Author getAuthorById(int id) {
        Author data= dao.findById(id).orElse(null); //Optional.ofNullable(dao.geAuthorById(id)).orElse(null);
        return data;
    }

    @Override
    public void addingAuthor(Author author, Set<Book> b) {

        if (author != null) {
            //dao.save(author);
            Author tmp = new Author(author.getFirstname(), author.getSecondname(), author.getMiddlename(), author.getDescription(), author.getDateofborn(), author.getDateofdead(), author.getCountry());
            dao.save(tmp);
            int id = dao.getMaxId();
            tmp = dao.findById(id).orElse(null);
            if (tmp!=null){
                for (Book t : b){
                    Merge m = new Merge(t, tmp);
                    md.save(m);
                }

            }
        }
    }

    @Override
    public void updatingAuthor(int auid, Author author) {
        if (auid>0 && author!=null){
            Author data = dao.findById(auid).orElse(null); //Optional.ofNullable(dao.geAuthorById(auid)).orElse(null);
            if (data!=null){
                data.setBooks(author.getBooks());
                data.setFirstname(author.getFirstname());
                data.setSecondname(author.getSecondname());
                data.setMiddlename(author.getMiddlename());
                data.setDateofborn(author.getDateofborn());
                data.setDateofdead(author.getDateofdead());
                data.setDescription(author.getDescription());
                dao.save(data);
            }

        }
    }

    @Override
    public void removingAuthor(int auid) {
        if (auid>=0){
            dao.deleteById(auid);
        }
    }

    @Override
    public Set<Book> getBooks(int auid) {
        Set<Book> books = dao.getBooks(auid);
        return books;
    }*/


    /*@Override
    public Set<Author> getAllAuthors() {
        Set<Author> authors = new HashSet();
         authors = new HashSet(dao.findAll());
        return authors;
    }

    @Override
    public Author getAuthorById(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void addingAuthor(Author author) {
        if (author!=null){
             dao.addingAuthor(author);
        }
    }

    @Override
    public void updatingAuthor(int auid, Author author) {
        Author data =  Optional.ofNullable(dao.getAuthorById(auid)).orElse(null);
        if (data!=null){
            data.setBooks(author.getBooks());
            data.setDateofborn(author.getDateofborn());
            data.setDateofdead(author.getDateofdead());
            data.setFirstname(author.getFirstname());
            data.setSecondname(author.getSecondname());
            data.setMiddlename(author.getMiddlename());
            data.setDescription(author.getDescription());
            data.setCountry(author.getCountry());
            dao.updatingAuthor(data.getId(),data);
        }
    }

    @Override
    public void removingAuthor(int auid) {
        Author author = Optional.ofNullable(dao.getAuthorById(auid)).orElse(null);
        if (author!=null){
            dao.removingAuthor(author.getId());
        }
    }*/
}
