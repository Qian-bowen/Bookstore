package com.sisyphe.bookstore.Json;

import com.sisyphe.bookstore.entity.Book;

import java.util.ArrayList;
import java.util.List;

/*
* books and cart_piece match accordingly
* */
public class CartJsonSend {
    public int user_id;
    public List<Integer> cart_piece=new ArrayList<>();
    public List<Book> books=new ArrayList<>();
}
