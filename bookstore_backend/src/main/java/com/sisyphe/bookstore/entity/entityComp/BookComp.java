package com.sisyphe.bookstore.entity.entityComp;

import com.sisyphe.bookstore.entity.Book;

import javax.persistence.Entity;


public class BookComp {
    private int bookId;
    private Long sell_num;

    public BookComp() {
    }

    public BookComp(int bookId, Long sell_num) {
        this.bookId = bookId;
        this.sell_num = sell_num;
    }

    public int getBookId() {
        return bookId;
    }

    public Long getSell_num() {
        return sell_num;
    }
}
