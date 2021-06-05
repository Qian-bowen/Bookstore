package com.sisyphe.bookstore.domain;

import com.sisyphe.bookstore.entity.Book;

public class BookBuyStat {
    Book book;
    int total_num;
    int total_price;
    public BookBuyStat(Book book, int total_num, int total_price)
    {
        this.book=book;
        this.total_num=total_num;
        this.total_price=total_price;
    }
}
