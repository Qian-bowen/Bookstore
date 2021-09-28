package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.dao.OrderDao;
import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.entity.entityComp.BookBuyComp;
import com.sisyphe.bookstore.entity.entityComp.BookComp;
import com.sisyphe.bookstore.entity.entityComp.UserConsumeComp;
import com.sisyphe.bookstore.repository.OrderItemRepository;
import com.sisyphe.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.REQUIRED)
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
    public List<Order> getOrders(Integer fetch_num,Integer fetch_begin)
    {
        return orderRepository.getOrders();
    }

    @Override
    public List<Order> searchOrderByUser(int user_id)
    {
        List<Order> orders = orderRepository.searchOrderByUser(user_id);
        return orders;
    }

    @Override
    public List<Order> searchOrderByUserBook(int user_id, int bookId)
    {
        return orderRepository.searchOrderByUserBook(user_id,bookId);
    }

    @Override
    public List<Order> searchOrderByUserTime(int user_id,Timestamp lt, Timestamp ut)
    {
        return orderRepository.searchOrderByUserTime(user_id,lt,ut);
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
    public List<BookComp> bestSellBook(int num, Timestamp lt, Timestamp ut)
    {
        Pageable pageRequest=PageRequest.of(0, num);
        return orderItemRepository.bestSellBook(pageRequest,lt,ut);
    }

    @Override
    public List<UserConsumeComp> userMostConsume(int num, Timestamp lt, Timestamp ut)
    {
        Pageable pageRequest=PageRequest.of(0, num);
        return orderRepository.userMostConsume(pageRequest,lt,ut);
    }

    @Override
    public List<BookBuyComp> PersonalMostConsume(int num, int user_id, Timestamp lt, Timestamp ut)
    {
        Pageable pageRequest=PageRequest.of(0, num);
        return orderRepository.PersonalMostConsume(pageRequest,user_id,lt,ut);
    }

}
