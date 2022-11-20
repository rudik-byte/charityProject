package com.takebook.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "books")
@EqualsAndHashCode(exclude = "authors")
@ToString(exclude = "authors")
public class Book {
    @Id
    @GenericGenerator(name = "primaryIncrement", strategy = "increment")
    @GeneratedValue(generator = "primaryIncrement")
    private Long id;

    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Author> authors;

    private String name;

    private String publisher;

    private Date publishDate;

    private String rating;

    private String isbn;

    @OneToOne
    private Genre genre;

    private boolean rented;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Rent> rentedBooks;
}
