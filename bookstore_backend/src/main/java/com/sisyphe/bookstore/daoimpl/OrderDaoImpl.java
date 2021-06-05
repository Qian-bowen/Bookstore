package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.dao.OrderDao;
import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.entity.entityComp.BookSellComp;
import com.sisyphe.bookstore.repository.OrderItemRepository;
import com.sisyphe.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public class OrderDaoImpl implements OrderDao {

    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;

    @Autowired
    public OrderDaoImpl(OrderRepository orderRepository,OrderItemRepository orderItemRepository)
    {
        this.orderRepository=orderRepository;
        this.orderItemRepository=orderItemRepository;
    }

    @Override
    public Order storeOrder(Order order, List<OrderItem> items)
    {
        Order order_saved=orderRepository.save(order);
        for(OrderItem item:items)
        {
            item.setOrder(order_saved);
            orderItemRepository.save(item);
        }
        order_saved.setOrderItems(items);
        return order_saved;
    }

    @Override
    public List<Order> searchOrderByUser(int user_id)
    {
        return orderRepository.searchOrderByUser(user_id);
    }

    @Override
    public List<Order> searchOrderByTime(Timestamp lower_time, Timestamp upper_time)
    {
        System.out.println("lower:"+lower_time+" upper:"+upper_time);
        return orderRepository.searchOrderByTime(lower_time,upper_time);
    }

    @Override
    public List<Order> searchOrderByBookId(int bookId)
    {
        return orderRepository.searchOrderByBookId(bookId);
    }

    @Override
    public List<BookSellComp> bestSellBook(int num)
    {
        Pageable pageRequest=PageRequest.of(0, num);
        return orderItemRepository.bestSellBook(pageRequest);
    }

}
