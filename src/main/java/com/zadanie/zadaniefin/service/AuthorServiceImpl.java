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

    private BookDao d1;
    @Autowired
    public void setD1(BookDao bd){
        this.d1 = bd;
    }
    @Autowired
    public void setDao(AuthorDao dao) {
        this.dao = dao;
    }
    @Override
    public Set<AuthorDTO> getAllAuthors() {
        List<Author> items = dao.findAll();
        Set<AuthorDTO> dto = new HashSet();
        for (Author au : items){
            AuthorDTO tmp = new AuthorDTO(au.getId(), au.getFirstname(), au.getSecondname(), au.getMiddlename(), au.getDateofborn(), au.getDateofdead(), au.getCountry(), au.getDescription());
            tmp.setBooks(au.getBooks());
            dto.add(tmp);
        }
        return dto;
    }

    @Override
    public AuthorDTO getAuthorById(int id) {
        Author a = dao.findById(id).orElse(null);
        AuthorDTO dto = new AuthorDTO();
        if (a!=null){
            dto = new AuthorDTO(a.getId(), a.getFirstname(), a.getSecondname(), a.getMiddlename(), a.getDateofborn(), a.getDateofdead(), a.getCountry(), a.getDescription());
            dto.setBooks(a.getBooks());
        }
        return dto;
    }

    @Override
    public void addingAuthor(AuthorDTO author) {
        if (author!=null){
            Author tmp = new Author(author.getFirstname(),author.getSecondname(), author.getMiddlename(), author.getDateofBorn(), author.getDateOfDead(), author.getCountry(), author.getDescription());
            tmp.setBooks(author.getBooks());
            dao.save(tmp);
        }
    }

    @Override
    public void updatingAuthor(AuthorDTO author) {

        Author au = dao.findById(author.getId()).orElse(null);
        if (au!=null){
            Set<Book> tmp = au.getBooks();
            Set<BookDTO> bta = author.getBookstoadd();
            Set<BookDTO> btr = author.getBookstoremove();
            if (bta!=null){
                for (BookDTO b : bta){
                    Book data = new Book(b.getId(), b.getName(), b.getIsbn(), b.getDateofwriting(), b.getDescription());
                    data.setAuthors(b.getAuthors());
                    tmp.add(data);
                }
            }

            if (btr!=null){
                for (BookDTO b : btr){
                    Book data = d1.findById(b.getId()).orElse(null);
                    if (data!=null){
                        tmp.remove(data);
                    }
                }
            }

            au.setFirstname(author.getFirstname());
            au.setSecondname(author.getSecondname());
            au.setMiddlename(author.getMiddlename());
            au.setDateofborn(author.getDateofBorn());
            au.setDateofdead(author.getDateOfDead());
            au.setCountry(author.getCountry());
            au.setDescription(author.getDescription());
            au.setBooks(tmp);
            dao.save(au);
        }
    }

    @Override
    public void removingAuthor(int auid) {
        dao.deleteById(auid);
    }
}
