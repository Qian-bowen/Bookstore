package com.sisyphe.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sisyphe.bookstore.Json.BookJson;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "book")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bookId")
public class Book {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name="isbn")
    private String isbn;

    @Column(name="name")
    private String name;

    @Column(name="type")
    private String type;

    @Column(name="author")
    private String author;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="description")
    private String description;

    @Column(name="inventory")
    private Integer inventory;

    @Column(name="image")
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

    public Book(BookJson bookJson)
    {
        bookId=bookJson.bookId;
        isbn= bookJson.isbn;
        name= bookJson.name;
        type= bookJson.type;
        author= bookJson.author;
        price= bookJson.price;
        description= bookJson.description;
        inventory= bookJson.inventory;
        image= bookJson.image;
    }

    public String get_name()
    {
        return name;
    }
    public int getBookId(){return bookId;}
    public String getIsbn(){return isbn;}
    public String getType(){return type;}
    public String getAuthor(){return author;}
    public BigDecimal getPrice(){return price;}
    public Integer getInventory(){return inventory;}

    public boolean reduceInventory(Integer reduceNum)
    {
        if(reduceNum>inventory)
            return false;
        inventory-=reduceNum;
        return true;
    }

}
