package com.sisyphe.bookstore.controller;

import com.google.gson.Gson;
import com.sisyphe.bookstore.Json.OrderJsonRec;
import com.sisyphe.bookstore.Json.OrderJsonSend;
import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.constant.Operation;
import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.entityId.CartId;
import com.sisyphe.bookstore.service.CartService;
import com.sisyphe.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sisyphe.bookstore.entity.Order;

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
        Order stored_order=orderService.storeOrder(orderJsonRec);
        OrderJsonSend orderJsonSend=new OrderJsonSend(stored_order);
        //remove from cart after make order
        List<OrderItem> items=stored_order.get_items();
        int user_id=stored_order.get_user_id();
        for(OrderItem item:items)
        {
            CartId cartId=new CartId(user_id,item.get_book_id());
            cartService.modifyCartItem(cartId, Operation.DEL);
        }

        return gson.toJson(orderJsonSend);
    }
}
