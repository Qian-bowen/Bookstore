package com.sisyphe.bookstore.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/*
* every piece of item you purchase
*  */
@Entity
@Table(name="orders")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "order_id")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    private int user_id;
    private BigDecimal total_price;

    @Basic(optional = false)
    @Column(name = "created_time", insertable = false, updatable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    //TODO:modified from order to orders maybe bugs
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order()
    {

    }

    public Order(int user_id,BigDecimal total_price,Timestamp timestamp,List<OrderItem> orderItems)
    {
        this.user_id=user_id;
        this.total_price=total_price;
        this.timestamp=timestamp;
        this.orderItems=orderItems;
    }

    public Order(int user_id,BigDecimal total_price)
    {
        this.user_id=user_id;
        this.total_price=total_price;
    }

    public int get_user_id(){return user_id;}
    public BigDecimal get_total_price(){return total_price;}
    public Timestamp get_timestamp(){return timestamp;}
    public List<OrderItem> get_items(){return orderItems;}

    public void set_timestamp(Timestamp stamp){timestamp=stamp;}
    public int get_order_id(){return order_id;}
    public void setOrderItems(List<OrderItem> orderItems){this.orderItems=orderItems;}

}
