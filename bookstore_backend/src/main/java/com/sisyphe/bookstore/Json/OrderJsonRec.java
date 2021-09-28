package com.sisyphe.bookstore.Json;

import lombok.Data;

import java.util.List;

@Data
public class OrderJsonRec {
    public List<OrderItemJson> orderItems;

    public OrderJsonRec(){}

    public OrderJsonRec(List<OrderItemJson> orderItems)
    {
        this.orderItems=orderItems;
    }
}
