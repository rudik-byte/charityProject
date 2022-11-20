package com.takebook.demo.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "authors")
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
public class Author {
    @Id
    @GenericGenerator(name = "primaryIncrement", strategy = "increment")
    @GeneratedValue(generator = "primaryIncrement")
    private Long id;

    private String name;

    private String gender;

    private Date dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "authors_book",
                joinColumns = @JoinColumn(name = "author_id"),
                inverseJoinColumns = @JoinColumn(name = "books_id")
    )
    private Set<Book> books;
}
