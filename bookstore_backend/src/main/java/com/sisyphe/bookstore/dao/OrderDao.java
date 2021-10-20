package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.entity.entityComp.BookBuyComp;
import com.sisyphe.bookstore.entity.entityComp.BookComp;
import com.sisyphe.bookstore.entity.entityComp.UserConsumeComp;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface OrderDao {
    Order storeOrder(Order order, List<OrderItem> items);

    List<Order> getOrders(Integer fetch_num, Integer fetch_begin);

    List<Order> searchOrderByUser(int user_id);

    List<Order> searchOrderByUserBook(int user_id, int bookId);

    List<Order> searchOrderByUserTime(int user_id, Timestamp lt, Timestamp ut);

    List<Order> searchOrderByTime(Timestamp lower_time, Timestamp upper_time);

    List<Order> searchOrderByBookId(int bookId);

    List<BookComp> bestSellBook(int num, Timestamp lt, Timestamp ut);

    List<UserConsumeComp> userMostConsume(int num, Timestamp lt, Timestamp ut);

    List<BookBuyComp> PersonalMostConsume(int num, int user_id, Timestamp lt, Timestamp ut);
}
