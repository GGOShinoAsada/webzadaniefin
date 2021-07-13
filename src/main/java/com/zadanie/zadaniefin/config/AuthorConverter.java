package com.zadanie.zadaniefin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import com.zadanie.zadaniefin.dao.*;
import com.zadanie.zadaniefin.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter implements Converter<String, Author> {
private AuthorDao dao;
private Logger logger = LoggerFactory.getLogger(AuthorConverter.class);
@Autowired
    public void setDao(AuthorDao dao) {
        this.dao = dao;
    }


    @Override
    public Author convert(String s) {
    Author a = new Author();
    try{
            int id = Integer.parseInt(s);
            a = dao.findById(id).orElse(null);
        }
        catch (Exception e){
            logger.error("exception "+e.getStackTrace());
        }
        return a;
    }
}
