package com.takebook.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "rents")
public class Rent {

    public Rent(Book book, User user){
        this.book = book;
        this.user = user;
        this.dateRented = Date.from(Instant.from(LocalDateTime.now()));
        this.dateDue = Date.from(Instant.from(LocalDateTime.now().plusSeconds(1)));
        this.overdue = false;
        this.returned = false;
    }

    @Id
    @GenericGenerator(name = "primaryIncrement", strategy = "increment")
    @GeneratedValue(generator = "primaryIncrement")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal price;

    private Date dateRented;

    private Date dateDue;

    private Date dateReturned;

    private boolean overdue;

    private boolean returned;
}
