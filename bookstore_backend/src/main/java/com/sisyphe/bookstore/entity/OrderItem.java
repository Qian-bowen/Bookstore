package com.sisyphe.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "orderitem")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "order_item_id")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_item_id;
    private int book_id;
    private int piece;
    private BigDecimal price;

    @Basic(optional = false)
    @Column(name = "created_time", insertable = false, updatable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public OrderItem() {

    }

    public OrderItem(int book_id, int piece, BigDecimal price) {
        this.book_id = book_id;
        this.piece = piece;
        this.price = price;
    }

    public int get_book_id() {
        return book_id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public BigDecimal get_price() {
        return price;
    }

    public Timestamp get_timestamp() {
        return timestamp;
    }

    public int getPiece() {
        return piece;
    }
}
