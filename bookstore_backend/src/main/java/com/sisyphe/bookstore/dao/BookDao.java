package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.entity.Book;

import java.util.List;

public interface BookDao {
    Book findOne(Integer id);

    List<Book> getBooks(Integer fetch_num,Integer fetch_begin);
}