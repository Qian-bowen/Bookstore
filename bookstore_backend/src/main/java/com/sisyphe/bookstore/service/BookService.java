package com.sisyphe.bookstore.service;

import com.sisyphe.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    Book findBookById(Integer id);
    List<Book> getBooks(Integer fetch_num,Integer fetch_begin);
}
