package com.sisyphe.bookstore.service;

import com.sisyphe.bookstore.entity.Order;

import java.sql.Timestamp;

public interface OrderService {
    Order storeOrder(Order order);
}
