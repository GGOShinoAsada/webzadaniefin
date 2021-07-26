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
        Set<AuthorDTO> data = service.getAllAuthors();
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
    public String createPost(AuthorDTO author){
        service.addingAuthor(author);
        return "redirect:/authors/index";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        AuthorDTO author = service.getAuthorById(id);
        Set<BookDTO> books = new HashSet();
        Set<BookDTO> allbooks = ser.getAllBooks();

        //get all books, exclude author's books
        books = new HashSet();
        Set<Book> tmp = author.getBooks();
        for (BookDTO b: allbooks){
            if (!isBookContains(tmp,b)){
                books.add(b);
            }
        }
        author.setBookstoadd(books);
        //get all books, include author
        books = new HashSet();
        for (BookDTO b: allbooks){
            if (isBookContains(tmp,b)){
                books.add(b);
            }
        }
        author.setBookstoremove(books);
        model.addAttribute("author", author);
        Set<BookDTO> bta = author.getBookstoadd();
        Set<BookDTO> btr = author.getBookstoremove();
        author.setBookstoadd(new HashSet());
        author.setBookstoremove(new HashSet());
        model.addAttribute("bookstoadd", bta);
        model.addAttribute("bookstoremove", btr);
        return "authors/edit";
    }
    @PostMapping("/edit")
    public String editPost(AuthorDTO author){
        if (author!=null){
            service.updatingAuthor(author);
        }
        else {
            logger.error("please enter correct values");
        }
        return "redirect:/authors/index";
    }
    @GetMapping("/delete/{id}")
    public String deleteauthor(@PathVariable("id") int id){
        service.removingAuthor(id);
        return "redirect:/authors/index";
    }
    private final boolean isBookContains(Set<Book> dto, BookDTO book){
        boolean flag = false;
        for (Book tmp : dto){
            if (tmp.getId()==book.getId()){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
