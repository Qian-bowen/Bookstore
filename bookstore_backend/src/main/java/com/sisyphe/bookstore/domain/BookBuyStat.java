package com.sisyphe.bookstore.domain;

import com.sisyphe.bookstore.entity.Book;

import java.math.BigDecimal;

public class BookBuyStat {
    Book book;
    Long total_num;
    BigDecimal total_price;

    public BookBuyStat() {
    }

    public BookBuyStat(Book book, Long total_num, BigDecimal total_price) {
        this.book = book;
        this.total_num = total_num;
        this.total_price = total_price;
    }
}
