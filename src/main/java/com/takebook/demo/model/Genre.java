package com.takebook.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "genres")
public class Genre {
    @Id
    @GenericGenerator(name = "primaryIncrement", strategy = "increment")
    @GeneratedValue(generator = "primaryIncrement")
    private Long id;

    private String name;
}
