package com.zadanie.zadaniefin.model;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuthorDTO {
    @Column(name = "id", unique = true)
    private int id;
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;
    @Column(name = "secondname", nullable = false, length = 50)
    private String secondname;
    @Column(name = "middlename", nullable = false, length = 50)
    private String middlename;
    @Min(0)
    @Column(name = "dateofborn")
    private int dateofBorn;
    @Min(0)
    @Column(name = "dateofdead")
    private int dateOfDead;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Column(name = "books")
    private Set<Book> books;
    @Column(name = "bookstoadd")
    private Set<BookDTO> bookstoadd;
    @Column(name = "bookstoremove")
    private Set<BookDTO> bookstoremove;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDTO author = (AuthorDTO) o;
        return author!=null? author.getId()==id : false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public AuthorDTO(String firstname, String secondname, String middlename, int dateofBorn, int dateOfDead, String country, String description) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.middlename = middlename;
        this.dateofBorn = dateofBorn;
        this.dateOfDead = dateOfDead;
        this.country = country;
        this.description = description;
    }

    public AuthorDTO(int id, String firstname, String secondname, String middlename, int dateofBorn, int dateOfDead, String country, String description) {
        this.id = id;
        this.firstname = firstname;
        this.secondname = secondname;
        this.middlename = middlename;
        this.description = description;
        this.dateofBorn = dateofBorn;
        this.dateOfDead = dateOfDead;
        this.country = country;
    }
}
