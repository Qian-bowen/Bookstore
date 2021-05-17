package com.sisyphe.bookstore.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sisyphe.bookstore.Json.OrderJsonRec;
import com.sisyphe.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.domain.OrderItem;

import java.sql.Timestamp;
import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/cart/order",method = RequestMethod.POST)
    @CrossOrigin
    public String storeOrder(@RequestBody String order_str)
    {
        System.out.println("order str:"+order_str);

        Gson gson=new Gson();
        OrderJsonRec orderJsonRec=gson.fromJson(order_str, OrderJsonRec.class);
        Order order=new Order(orderJsonRec);
        Order stored_order=orderService.storeOrder(order);
        return gson.toJson(stored_order);
    }
}
