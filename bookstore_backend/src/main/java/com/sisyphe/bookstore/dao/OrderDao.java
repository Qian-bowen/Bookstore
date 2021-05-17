package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.entity.Order;

import java.sql.Timestamp;

public interface OrderDao {
    Order storeOrder(Order order);
}
