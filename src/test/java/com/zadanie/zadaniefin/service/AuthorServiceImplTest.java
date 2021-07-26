package com.zadanie.zadaniefin.service;

import com.zadanie.zadaniefin.dao.*;
import com.zadanie.zadaniefin.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.*;
@SpringBootTest
public class AuthorServiceImplTest {
    @Qualifier("getAuthors")
    @Autowired
    private AuthorService service;
    @Before
    public void setUp() throws Exception {
        Set<Author> authors = new HashSet();
        Set<Book> books = new HashSet<>();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllAuthors() {
       // Set<Author> authors = new HashSet();
        //authors = service.getAllAuthors();
        //Assert.assertEquals(authors.size(),2);
        //Set<Author> new HashSet<>(authors = dao.findAll().iterator().o)
    }

    @Test
    public void getAuthorById() {
    }

    @Test
    public void addingAuthor() {
    }

    @Test
    public void updatingAuthor() {
    }

    @Test
    public void removingAuthor() {
    }
}