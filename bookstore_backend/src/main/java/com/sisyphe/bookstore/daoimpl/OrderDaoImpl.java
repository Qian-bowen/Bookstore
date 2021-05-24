package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.dao.OrderDao;
import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.repository.OrderItemRepository;
import com.sisyphe.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
