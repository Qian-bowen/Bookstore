package com.sisyphe.bookstore.Json;

import java.util.List;

public class OrderJsonRec {
    public int user_id;
    public double total_price;
    public List<OrderItemJsonRec> orderItems;

    public OrderJsonRec(){}
}
