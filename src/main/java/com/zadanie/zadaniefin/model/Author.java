package com.zadanie.zadaniefin.model;
import lombok.*;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname", length = 50, nullable = false)
    private String firstname;
    @Column(name = "secondname", length = 50, nullable = false)
    private String secondname;
    @Column(nullable = false, length = 50, name = "middlename")
    private String middlename;
    @Column(name = "description", length = 100, nullable = false)
    private String description;
    @Min(0)
    @Column(nullable = false, name = "dateofborn")
    private int dateofborn;
    @Column(name = "dateofdead", nullable = false)
    @Min(0)
    private int dateofdead;
    @Column(name = "country", nullable = false)
    private String country;

    public Author(String firstname, String secondname, String middlename, int dateofborn, int dateofdead, String country, String description) {
        this.firstname = firstname;
        this.secondname = secondname;
        this.middlename = middlename;
        this.description = description;
        this.dateofborn = dateofborn;
        this.dateofdead = dateofdead;
        this.country = country;
    }

    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns
                    = @JoinColumn(name = "auid", referencedColumnName = "id"),
            inverseJoinColumns
                    = @JoinColumn(name = "bid", referencedColumnName = "id")
    )
    private Set<Book> books= new HashSet();

    public Author(int id, String firstname, String secondname, String middlename, int dateofBorn, int dateOfDead, String country, String description) {
        this.id=id;
        this.firstname=firstname;
        this.secondname=secondname;
        this.middlename=middlename;
        this.dateofborn=dateofBorn;
        this.dateofdead=dateOfDead;
        this.country=country;
        this.description=description;
    }
}
