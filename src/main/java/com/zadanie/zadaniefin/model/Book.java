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
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "isbn", nullable = false)
    private String isbn;
    @Column(name = "dateofwriting", nullable = false)
    private int dateofwriting;
    @Column(name = "description", nullable = false)
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Book(String name, String isbn, int dateofwriting, String description) {
        this.name = name;
        this.isbn = isbn;
        this.dateofwriting = dateofwriting;
        this.description = description;
    }
    @Transient
    private Set<Author> authorsToAdd = new HashSet();
    @Transient
    private Set<Author> authorsToRemove = new HashSet();
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
