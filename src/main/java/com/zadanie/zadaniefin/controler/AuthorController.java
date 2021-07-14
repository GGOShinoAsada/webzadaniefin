package com.zadanie.zadaniefin.controler;

import com.zadanie.zadaniefin.model.*;
import com.zadanie.zadaniefin.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("/authors")
public class AuthorController {
    private Logger logger = LoggerFactory.getLogger(AuthorController.class);
    private BookService ser;
    @Autowired
    public void setSer(@Qualifier("getBooks") BookService ser) {
        this.ser = ser;
    }

    private AuthorService service;
    @Autowired
    public void setService(@Qualifier("getAuthors") AuthorService service) {
        this.service = service;
    }


    @GetMapping("/index")
    public String index(Model model){
        Set<Author> data = new HashSet();
        data = service.getAllAuthors();

        model.addAttribute("authors",data);
        return "authors/index";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") int id, Model model){
        model.addAttribute("author", service.getAuthorById(id));
        return "authors/details";
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("allbooks", ser.getAllBooks());
        model.addAttribute("author", new Author());
        return "authors/create";
    }
    @PostMapping("/create")
    public String createPost(Author author){


        service.addingAuthor(author);
        return "redirect:/authors/index";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        Author author = service.getAuthorById(id);
        Set<Book> books = new HashSet();
        Set<Book> allbooks = ser.getAllBooks();

        //get all books, exclude author's books
        books = new HashSet();
        int ind = 0;
        Set<Book> tmp = author.getBooks();
        for (Book b: allbooks){
            if (!tmp.contains(b)){
                books.add(b);
            }
        }
        author.setBooksToAdd(books);
        //get all books, include author
        books = new HashSet();
        for (Book b: allbooks){
            if (tmp.contains(b)){
                books.add(b);
            }
        }
        author.setBooksToRemove(books);

        model.addAttribute("author", author);
        Set<Book> bta = author.getBooksToAdd();
        Set<Book> btr = author.getBooksToRemove();
        author.setBooksToRemove(new HashSet());
        author.setBooksToAdd(new HashSet());
        model.addAttribute("bookstoadd", bta);
        model.addAttribute("bookstoremove", btr);
        return "authors/edit";
    }
    @PostMapping("/edit")
    public String editPost(Author author){
        Author au = service.getAuthorById(author.getId());
        Set<Book> tmp = au.getBooks();

        Set<Book> ba = Optional.ofNullable(author.getBooksToAdd()).orElse(null);
        Set<Book> br = Optional.ofNullable(author.getBooksToRemove()).orElse(null);
        if (ba!=null){
            for (Book b : author.getBooksToAdd()){
                tmp.add(b);
            }
        }
       if (br!=null){
           for (Book b: author.getBooksToRemove()){
               tmp.remove(b);
           }
       }

        au.setBooks(tmp);
        service.updatingAuthor(au.getId(), au);

        return "redirect:/authors/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteauthor(@PathVariable("id") int id){
        service.removingAuthor(id);
        return "redirect:/authors/index";
    }
}
