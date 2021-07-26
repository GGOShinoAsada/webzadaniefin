package com.zadanie.zadaniefin.model;

import com.sun.istack.Nullable;
import lombok.*;

import java.util.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;
    @Column(name = "dateofwriting", nullable = false)
    private int dateofwriting;
    @Column(name = "description", length = 100, nullable = false)
    private String description;

    public Book(int id, String name, String isbn, int dateofwriting, String description) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.dateofwriting = dateofwriting;
        this.description = description;
    }

    public Book(String name, String isbn, int dateofwriting, String description) {
        this.name = name;
        this.isbn = isbn;
        this.dateofwriting = dateofwriting;
        this.description = description;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "books_authors",
            joinColumns
                    = @JoinColumn(name = "bid", referencedColumnName = "id"),
            inverseJoinColumns
                    = @JoinColumn(name = "auid", referencedColumnName = "id")
    )
    private Set<Author> authors= new HashSet();
}
