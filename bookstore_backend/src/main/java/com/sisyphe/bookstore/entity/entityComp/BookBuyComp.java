package com.sisyphe.bookstore.entity.entityComp;


import java.math.BigDecimal;

public class BookBuyComp {
    int bookId;
    Long total_num;
    BigDecimal item_price;


    public BookBuyComp(int bookId, Long total_num, BigDecimal item_price)
    {
        this.bookId=bookId;
        this.total_num=total_num;
        this.item_price = item_price;
    }

    public int getBookId() {
        return bookId;
    }

    public BigDecimal getItem_price() {
        return item_price;
    }

    public Long getTotal_num() {
        return total_num;
    }
}
