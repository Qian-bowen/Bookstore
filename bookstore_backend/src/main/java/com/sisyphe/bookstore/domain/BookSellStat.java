package com.sisyphe.bookstore.domain;

import com.sisyphe.bookstore.entity.Book;

public class BookSellStat {
    Book book;
    Long sell_num;
    public BookSellStat(Book book, Long sell_num)
    {
        this.book=book;
        this.sell_num=sell_num;
    }
}
