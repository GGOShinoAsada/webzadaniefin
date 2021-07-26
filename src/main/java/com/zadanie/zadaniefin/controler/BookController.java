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
        BookDTO b = service.getBookById(id);
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
    public String createPost(BookDTO book){
        service.addingBook(book);
        return "redirect:/books/index";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        BookDTO book = service.getBookById(id);
        Set<AuthorDTO> authors = new HashSet();
        authors = ser.getAllAuthors();
        Set<Author> tmp = book.getAuthors();
        Set<AuthorDTO> data = new HashSet();
        for (AuthorDTO a: authors){
            if (!isAuthorContains(tmp,a)){
                data.add(a);
            }
        }
        book.setAuthorsToAdd(data);
        data = new HashSet();
        for (AuthorDTO a: authors){
            if (isAuthorContains(tmp,a)){
                data.add(a);
            }
        }
        book.setAuthorToRemove(data);
        model.addAttribute("book", book);
        Set<AuthorDTO> atoadd = book.getAuthorsToAdd();
        Set<AuthorDTO> atorem = book.getAuthorToRemove();
        book.setAuthorToRemove(new HashSet());
        book.setAuthorsToAdd(new HashSet());
        model.addAttribute("authtoadd", atoadd);
        model.addAttribute("authtoremove",atorem);
        return "books/edit";
    }
    @PostMapping("/edit")
    public String editpost(BookDTO book){
        if (book!=null){
            service.updatingBook(book);
        }
        else {
            logger.error("please enter correct values");
        }
        return "redirect:/books/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteget(@PathVariable("id") int id){
        service.removingBook(id);
        return "redirect:/books/index";
    }
    private final boolean isAuthorContains(Set<Author> dto, AuthorDTO author){
        boolean flag = false;
        for (Author tmp : dto){
            if (tmp.getId()==author.getId()){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
