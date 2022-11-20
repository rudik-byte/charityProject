package com.takebook.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.takebook.demo.model.type.EnumPostgreSQLType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "users")
@TypeDef(
        name = "user_role", typeClass = EnumPostgreSQLType.class)
@TypeDef(
        name = "account_type", typeClass = EnumPostgreSQLType.class)
public class User {
    @Id
    @GenericGenerator(name = "primaryIncrement", strategy = "increment")
    @GeneratedValue(generator = "primaryIncrement")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Type(type = "user_role")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Type(type = "account_type")
    private AccountType accountType;

    private boolean bookLimitReached;

    private int bookLimit;

    private int booksRented;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Rent> rentedBooks;


    @PrePersist
    public void prePersist() {
        if (bookLimit == 0) //We set default value in case if the value is not set yet.
            bookLimit = 3;
    }

}

