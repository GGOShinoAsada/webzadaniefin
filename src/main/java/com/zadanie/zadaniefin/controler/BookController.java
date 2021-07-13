package com.zadanie.zadaniefin.controler;


import com.zadanie.zadaniefin.model.*;
import com.zadanie.zadaniefin.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.*;
@Controller
@RequestMapping("/books")
public class BookController {
    private Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookService service;
    private AuthorService ser;
    @Autowired
    public void setService(@Qualifier("getBooks") BookService service) {
        this.service = service;
    }
    @Autowired
    public void setSer(@Qualifier("getAuthors") AuthorService ser) {
        this.ser = ser;
    }



    @GetMapping("/index")
    private String index(Model model) {
        model.addAttribute("books", service.getAllBooks());
        return "books/index";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") int id, Model model){
        Book b = service.getBookById(id);
        model.addAttribute("book", b );
        return "books/details";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("allauthors", ser.getAllAuthors());
        return "books/create";
    }
    @PostMapping("/create")
    public String createPost(Book book){
        int v =0;
        service.addingBook(book);
        return "redirect:/books/index";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        Book book = service.getBookById(id);
        Set<Author> authors = new HashSet();
        authors = ser.getAllAuthors();
        Set<Author> tmp = book.getAuthors();
        Set<Author> data = new HashSet();
        for (Author a: authors){
            if (!tmp.contains(a)){
                data.add(a);
            }
        }
        book.setAuthorsToAdd(data);
        data = new HashSet();
        for (Author a: authors){
            if (tmp.contains(a)){
                data.add(a);
            }
        }
        book.setAuthorsToRemove(data);
        model.addAttribute("book", book);
        Set<Author> atoadd = book.getAuthorsToAdd();
        Set<Author> atorem = book.getAuthorsToRemove();
        book.setAuthorsToRemove(new HashSet());
        book.setAuthorsToAdd(new HashSet());
        model.addAttribute("authtoadd", atoadd);
        model.addAttribute("authtoremove",atorem);
        return "books/edit";
    }
    @PostMapping("/edit")
    public String editpost(Book book){
        Book b = service.getBookById(book.getId());
        Set<Author> tmp = b.getAuthors();
        Set<Author> authtoadd = Optional.ofNullable(book.getAuthorsToAdd()).orElse(null);
        Set<Author> authtorem = Optional.ofNullable(book.getAuthorsToRemove()).orElse(null);
        if (authtoadd!=null){
            for (Author a: authtoadd){
                tmp.add(a);
            }
        }
        if (authtorem!=null){
            for (Author a: authtorem){
                tmp.remove(a);
            }
        }
        b.setAuthors(tmp);
        service.updatingBook(b.getId(), b);
        return "redirect:/books/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteget(@PathVariable("id") int id){
        service.removingBook(id);
        return "redirect:/books/index";
    }

}
