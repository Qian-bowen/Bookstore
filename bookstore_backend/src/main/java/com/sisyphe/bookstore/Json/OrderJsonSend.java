package com.sisyphe.bookstore.Json;

import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.entity.OrderItem;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderJsonSend  implements Serializable  {
    public int order_id;
    public int user_id;
    public BigDecimal total_price;
    public Timestamp timestamp;
    public List<OrderItemJson> orderItems;

    public OrderJsonSend()
    {

    }
    public OrderJsonSend(Order order)
    {
        this.order_id=order.get_order_id();
        this.user_id=order.get_user_id();
        this.total_price=order.get_total_price();
        this.timestamp=order.get_timestamp();
        this.orderItems=new ArrayList<>();
        List<OrderItem> orderItem=order.get_items();
        for(OrderItem item:orderItem)
        {
            OrderItemJson orderItemJson=new OrderItemJson(item.get_book_id(),item.getPiece());
            this.orderItems.add(orderItemJson);
        }
    }
}
