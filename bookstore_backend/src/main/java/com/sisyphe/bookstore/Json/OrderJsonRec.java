package com.sisyphe.bookstore.Json;

import java.util.List;

public class OrderJsonRec {
    public int user_id;
    public double total_price;
    public List<OrderItemJson> orderItems;

    public OrderJsonRec(){}
}
