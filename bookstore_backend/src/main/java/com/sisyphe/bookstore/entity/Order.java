package com.sisyphe.bookstore.entity;

import java.io.Serializable;
import java.sql.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sisyphe.bookstore.Json.OrderItemJsonRec;
import com.sisyphe.bookstore.Json.OrderJsonRec;
import com.sisyphe.bookstore.domain.OrderItem;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
* every piece of item you purchase
*  */
@Entity
@Table(name="orders")
public class Order implements Serializable {
    @Id
    private int order_id;
    private int user_id;
    private double total_price;
    private Timestamp timestamp;
    @ElementCollection
    private List<OrderItem> orderItems;

    public Order()
    {

    }

    public Order(int uid,double total,Timestamp stamp,List<OrderItem> items)
    {
        user_id=uid;
        total_price=total;
        timestamp=stamp;
        orderItems=items;
    }

    public Order(OrderJsonRec orderJson)
    {
        user_id=orderJson.user_id;
        total_price=orderJson.total_price;
        orderItems=new ArrayList<>();
        for(OrderItemJsonRec itemJsonRec : orderJson.orderItems)
        {
            OrderItem item=new OrderItem(itemJsonRec);
            orderItems.add(item);
        }
    }


    public int get_user_id(){return user_id;}
    public double get_total_price(){return total_price;}
    public Timestamp get_timestamp(){return timestamp;}
    public List<OrderItem> get_items(){return orderItems;}

    public void set_timestamp(Timestamp stamp){timestamp=stamp;}
    public void set_order_id(int id){order_id=id;}

}
