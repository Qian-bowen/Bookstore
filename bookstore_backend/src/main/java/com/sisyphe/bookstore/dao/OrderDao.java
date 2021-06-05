package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.domain.OrderSearch;
import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.entity.entityComp.BookSellComp;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDao {
    Order storeOrder(Order order, List<OrderItem> items);
    List<Order> searchOrderByUser(int user_id);
    List<Order> searchOrderByTime(Timestamp lower_time, Timestamp upper_time);
    List<Order> searchOrderByBookId(int bookId);
    List<BookSellComp> bestSellBook(int num);
}
