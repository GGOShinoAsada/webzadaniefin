package com.zadanie.zadaniefin.model;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @Column(name = "id",unique = true)
    private int id;
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "isbn",length = 20, unique = true)
    private String isbn;
    @Min(0)
    @Column(name = "dateofwriting")
    private int dateofwriting;
    @Column(name = "description", nullable = false, length = 100)
    private String description;
    @Column(name = "authors")
    private Set<Author> authors;
    @Column( name = "authorstoadd")
    private Set<AuthorDTO> authorsToAdd;
    @Column(name = "authortoremove")
    private Set<AuthorDTO> authorToRemove;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO book = (BookDTO) o;
        return id == book.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public BookDTO(int id, String name, String isbn, int dateofwriting, String description) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.dateofwriting = dateofwriting;
        this.description = description;
    }
}
