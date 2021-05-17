package com.sisyphe.bookstore.domain;

import com.sisyphe.bookstore.Json.OrderItemJsonRec;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderItem implements Serializable {
    private int book_id;
    private int order_id;
    private double price;
    private Timestamp timestamp;

    public OrderItem(int bid,int oid,double pce)
    {
        book_id=bid;
        order_id=oid;
        price=pce;
    }

    public OrderItem(OrderItemJsonRec item)
    {
        book_id=item.book_id;
        price=item.price;
    }

    public int get_book_id(){return book_id;}
    public int get_order_id(){return order_id;}
    public double get_price(){return price;}
    public Timestamp get_timestamp(){return timestamp;}
}
