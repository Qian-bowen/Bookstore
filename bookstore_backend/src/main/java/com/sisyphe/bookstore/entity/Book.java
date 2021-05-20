package com.sisyphe.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;


@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "bookId")
public class Book {

    @Id
    @Column(name="id")
    private int bookId;

    private String isbn;
    private String name;
    private String type;
    private String author;
    private BigDecimal price;
    private String description;
    private Integer inventory;
    private String image;

    public Book() { }

    public Book(int p_bid,String p_isbn,String p_name,String p_type,String p_author,BigDecimal p_price,String p_dsp,Integer p_ivt,String p_img)
    {
        bookId=p_bid;
        isbn=p_isbn;
        name=p_name;
        type=p_type;
        author=p_author;
        price=p_price;
        description=p_dsp;
        inventory=p_ivt;
        image=p_img;
    }

    public String get_name()
    {
        return name;
    }
}
