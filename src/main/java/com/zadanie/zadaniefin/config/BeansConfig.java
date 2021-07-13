package com.zadanie.zadaniefin.config;

import com.zadanie.zadaniefin.dao.*;
import com.zadanie.zadaniefin.service.AuthorService;
import com.zadanie.zadaniefin.service.AuthorServiceImpl;
import com.zadanie.zadaniefin.service.BookService;
import com.zadanie.zadaniefin.service.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public AuthorService getAuthors(){
        return new AuthorServiceImpl();
    }
    @Bean
    public BookService getBooks(){
        return new BookServiceImpl();
    }
    /*@Bean
    public BooksDao getBooksDao(){
        return new BooksDaoImpl();
    }
    @Bean
    public AuthorsDao getAuthorsDao(){
        return new AuthorsDaoImpl();
    }*/
}
