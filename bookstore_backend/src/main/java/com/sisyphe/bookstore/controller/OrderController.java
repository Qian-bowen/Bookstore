package com.sisyphe.bookstore.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sisyphe.bookstore.Json.OrderJsonRec;
import com.sisyphe.bookstore.constant.Operation;
import com.sisyphe.bookstore.entity.entityId.CartId;
import com.sisyphe.bookstore.service.CartService;
import com.sisyphe.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.domain.OrderItem;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class OrderController {

    private OrderService orderService;
    private CartService cartService;

    @Autowired
    public OrderController(OrderService orderService,CartService cartService)
    {
        this.orderService=orderService;
        this.cartService=cartService;
    }

    @RequestMapping(value="/cart/order",method = RequestMethod.POST)
    public String storeOrder(@RequestBody String order_str)
    {
        System.out.println("order str:"+order_str);

        Gson gson=new Gson();
        OrderJsonRec orderJsonRec=gson.fromJson(order_str, OrderJsonRec.class);
        Order order=new Order(orderJsonRec);
        Order stored_order=orderService.storeOrder(order);
        int user_id=order.get_user_id();

        //remove items in cart
        for(OrderItem orderItem : order.get_items())
        {
            CartId tmpId=new CartId(user_id,orderItem.get_book_id());
            cartService.modifyCartItem(tmpId, Operation.DEL);
        }

        return gson.toJson(stored_order);
    }
}
