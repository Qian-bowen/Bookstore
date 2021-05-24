package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.Order;

import java.util.List;

public interface OrderDao {
    Order storeOrder(Order order, List<OrderItem> items);
}
