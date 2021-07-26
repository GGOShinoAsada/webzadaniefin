package com.zadanie.zadaniefin.config;

import com.zadanie.zadaniefin.dao.*;
import com.zadanie.zadaniefin.model.Book;
import com.zadanie.zadaniefin.model.BookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class BookConverter implements Converter<String, BookDTO> {
    Logger logger = LoggerFactory.getLogger(BookConverter.class);
    @Autowired
    public void setDao(BookDao dao) {
        this.dao = dao;
    }

    private BookDao dao;
    @Override
    public BookDTO convert(String s) {
        BookDTO tmp = new BookDTO();
        try{
            int id = Integer.parseInt(s);
            Book b = dao.findById(id).orElse(null);
            if (b!=null){
                tmp = new BookDTO(b.getId(), b.getName(), b.getIsbn(), b.getDateofwriting(), b.getDescription());
                tmp.setAuthors(b.getAuthors());
            }
        }
        catch (Exception e){
            logger.error("check parameters");
        }
        return tmp;
    }
}
