package com.sisyphe.bookstore.service;

import com.sisyphe.bookstore.Json.OrderJsonRec;
import com.sisyphe.bookstore.domain.OrderSearch;
import com.sisyphe.bookstore.entity.Order;

import java.sql.Timestamp;
import java.util.List;

public interface OrderService {
    Order storeOrder(OrderJsonRec orderJsonRec);
    List<Order> searchOrderByUser(OrderSearch orderSearch);
    List<Order> searchOrderByTime(OrderSearch orderSearch);
    List<Order> searchOrderByBookName(OrderSearch orderSearch);
}
