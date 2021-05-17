package com.sisyphe.bookstore.entity;

public class Book {
    private int bookId;

    private String isbn;
    private String name;
    private String type;
    private String author;
    private Double price;
    private String description;
    private Integer inventory;
    private String image;

    public Book(int p_bid,String p_isbn,String p_name,String p_type,String p_author,Double p_price,String p_dsp,Integer p_ivt,String p_img)
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

    public Book()
    {

    }

    public String get_name()
    {
        return name;
    }
    public double get_price(){return price;}

}
