package com.zadanie.zadaniefin.model;
import lombok.*;

import java.util.*;
import javax.persistence.*;

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
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "secondname", nullable = false)
    private String secondname;
    @Column(nullable = false, name = "middlename")
    private String middlename;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(nullable = false, name = "dateofborn")
    private int dateofborn;
    @Column(name = "dateofdead", nullable = false)
    private int dateofdead;
    @Column(name = "COUNTRY", nullable = false)
    private String country;
    @Transient
    private Set<Book> booksToAdd;
    @Transient
    private Set<Book> booksToRemove;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return author!=null? author.getId()==id : false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Author(String firstname, String secondname, String middlename, String description, int dateofborn, int dateofdead, String country) {
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
}
