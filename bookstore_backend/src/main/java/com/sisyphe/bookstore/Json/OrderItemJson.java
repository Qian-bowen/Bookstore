package com.sisyphe.bookstore.Json;

import java.math.BigDecimal;

public class OrderItemJson {
    public int book_id;
    public int piece;
    public BigDecimal price;
    public OrderItemJson(){}
    public OrderItemJson(int book_id,int piece,BigDecimal price)
    {
        this.book_id=book_id;
        this.piece=piece;
        this.price=price;
    }
}
