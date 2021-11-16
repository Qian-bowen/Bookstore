package com.sisyphe.bookstore.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book_tag")
public class BookTag {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_id")
    private Integer book_id;

    @Column(name = "tag")
    private String tag;
}
