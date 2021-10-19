package com.bookstore.microservice.author.entity;


import javax.persistence.*;
import java.math.BigDecimal;



@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "inventory")
    private Integer inventory;

    @Lob
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    public Book() {
    }

    public Book(int p_bid, String p_isbn, String p_name, String p_type, String p_author, BigDecimal p_price, String p_dsp, Integer p_ivt, byte[] p_img) {
        bookId = p_bid;
        isbn = p_isbn;
        name = p_name;
        type = p_type;
        author = p_author;
        price = p_price;
        description = p_dsp;
        inventory = p_ivt;
        image = p_img;
    }

    public Book(String p_isbn, String p_name, String p_type, String p_author, BigDecimal p_price, String p_dsp, Integer p_ivt, byte[] p_img) {
        isbn = p_isbn;
        name = p_name;
        type = p_type;
        author = p_author;
        price = p_price;
        description = p_dsp;
        inventory = p_ivt;
        image = p_img;
    }


    public String get_name() {
        return name;
    }

    public int getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean reduceInventory(Integer reduceNum) {
        if (reduceNum > inventory)
            return false;
        inventory -= reduceNum;
        return true;
    }

}
