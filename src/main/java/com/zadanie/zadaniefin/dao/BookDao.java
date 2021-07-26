package com.zadanie.zadaniefin.dao;

import com.zadanie.zadaniefin.model.Author;
import com.zadanie.zadaniefin.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

}
