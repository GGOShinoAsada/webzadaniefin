package com.zadanie.zadaniefin.config;

import com.zadanie.zadaniefin.dao.*;
import com.zadanie.zadaniefin.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class BookConverter implements Converter<String, Book> {
    Logger logger = LoggerFactory.getLogger(BookConverter.class);
    @Autowired
    public void setDao(BookDao dao) {
        this.dao = dao;
    }

    private BookDao dao;
    @Override
    public Book convert(String s) {
        Book tmp = new Book();
        try
        {
            int b = Integer.parseInt(s);
            tmp = dao.findById(b).orElse(null);
        }
        catch (Exception e){
            logger.error("error "+e.getStackTrace());
        }
        return tmp;
    }
}
