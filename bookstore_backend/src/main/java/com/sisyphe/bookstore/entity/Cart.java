package com.sisyphe.bookstore.entity;

public class Cart {
    private int user_id;
    private int book_id;
    private int piece;
    public Cart(int uid,int bid,int p)
    {
        user_id=uid;
        book_id=bid;
        piece=p;
    }
    public int get_user_id(){return user_id;}
    public int get_book_id(){return book_id;}
    public int get_piece(){return piece;}
}
