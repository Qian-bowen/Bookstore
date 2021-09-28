package com.sisyphe.bookstore.Json;

import java.math.BigDecimal;

public class OrderItemJson {
    public int book_id;
    public int piece;
    public OrderItemJson(){}
    public OrderItemJson(int book_id,int piece)
    {
        this.book_id=book_id;
        this.piece=piece;
    }
}
