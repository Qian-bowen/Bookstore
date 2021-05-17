package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.dao.BookDao;
import com.sisyphe.bookstore.dao.OrderDao;
import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public Order storeOrder(Order order)
    {
        return orderDao.storeOrder(order);
    }
}
