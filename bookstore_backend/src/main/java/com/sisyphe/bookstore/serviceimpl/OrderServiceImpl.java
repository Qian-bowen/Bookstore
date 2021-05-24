package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.Json.OrderItemJson;
import com.sisyphe.bookstore.Json.OrderJsonRec;
import com.sisyphe.bookstore.dao.OrderDao;
import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao)
    {
        this.orderDao=orderDao;
    }

    @Override
    public Order storeOrder(OrderJsonRec orderJsonRec)
    {
        int user_id=orderJsonRec.user_id;
        BigDecimal total_price=new BigDecimal(orderJsonRec.total_price);
        List<OrderItemJson> orderItems=orderJsonRec.orderItems;

        Order order=new Order(user_id,total_price);
        List<OrderItem> items=new ArrayList<>();

        for(OrderItemJson itemJson:orderItems)
        {
            int book_id=itemJson.book_id;
            BigDecimal price=itemJson.price;
            OrderItem orderItem=new OrderItem(book_id,price,order);
            items.add(orderItem);
        }

        return orderDao.storeOrder(order,items);
    }
}
