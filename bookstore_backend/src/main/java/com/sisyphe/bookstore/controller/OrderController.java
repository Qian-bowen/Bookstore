package com.sisyphe.bookstore.controller;

import com.google.gson.Gson;
import com.sisyphe.bookstore.Json.OrderJsonRec;
import com.sisyphe.bookstore.Json.OrderJsonSend;
import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.constant.Operation;
import com.sisyphe.bookstore.constant.SearchType;
import com.sisyphe.bookstore.domain.OrderSearch;
import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.entityComp.CartId;
import com.sisyphe.bookstore.service.CartService;
import com.sisyphe.bookstore.service.OrderService;
import com.sisyphe.bookstore.utils.msgutils.Msg;
import com.sisyphe.bookstore.utils.msgutils.MsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import com.sisyphe.bookstore.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    private OrderService orderService;
    private CartService cartService;

    @Autowired
    private KafkaTemplate<String, OrderJsonRec> kafkaTemplate;

    @Autowired
    public OrderController(OrderService orderService,CartService cartService)
    {
        this.orderService=orderService;
        this.cartService=cartService;
    }

    @RequestMapping(value="/cart/order",method = RequestMethod.POST)
    public Msg storeOrder(@RequestBody String order_str)
    {
        System.out.println("order str:"+order_str);

        Gson gson=new Gson();
        OrderJsonRec orderJsonRec=gson.fromJson(order_str, OrderJsonRec.class);

        kafkaTemplate.send("order",orderJsonRec);

        return new Msg(MsgUtil.SUCCESS,"订单已接收");
    }

    /**
     * TODO:find a proper place for listener
     * @param orderJsonRec
     */
    @KafkaListener(topics = "order",containerFactory = "kafkaOrderListenerContainerFactory")
    private void handleOrder(OrderJsonRec orderJsonRec)
    {
        System.out.println("receive:"+orderJsonRec.user_id);
        Order stored_order=orderService.storeOrder(orderJsonRec);
        //remove from cart after make order
        List<OrderItem> items=stored_order.get_items();
        int user_id=stored_order.get_user_id();
        for(OrderItem item:items)
        {
            CartId cartId=new CartId(user_id,item.get_book_id());
            cartService.modifyCartItem(cartId, Operation.DEL);
        }
    }

    @RequestMapping(value="/order/get_orders")
    public String getOrders(@RequestBody Map<String,Integer> param)
    {
        Integer fetch_num=param.get(Constant.FETCH_NUM);
        Integer fetch_begin=param.get(Constant.FETCH_BEGIN);
        List<Order> orderList=orderService.getOrders(fetch_num,fetch_begin);
        System.out.println("orders:"+orderList.size());

        List<OrderJsonSend> sendOrders=new ArrayList<>();
        for(Order order : orderList)
        {
            OrderJsonSend orderJsonSend=new OrderJsonSend(order);
            sendOrders.add(orderJsonSend);
        }

        Gson gson=new Gson();
        return gson.toJson(sendOrders);
    }

    //TODO CHECK SESSION! EXCEPT ADMIN CANNOT SEARCH OTHERS ORDERS
    @RequestMapping(value="/order/search")
    public String searchOrder(@RequestBody OrderSearch orderSearch)
    {
        List<Order> orders;
        if(orderSearch.getType()== SearchType.BY_USER_ID)
        {
            orders=orderService.searchOrderByUser(orderSearch);
        }
        else if(orderSearch.getType()== SearchType.BY_TIME)
        {
            orders=orderService.searchOrderByTime(orderSearch);
        }
        else if(orderSearch.getType()== SearchType.BY_NAME)
        {
            orders=orderService.searchOrderByBookName(orderSearch);
        }
        else if(orderSearch.getType()==SearchType.BY_USER_BOOK)
        {
            orders=orderService.searchOrderByUserBook(orderSearch);
        }
        else if(orderSearch.getType()==SearchType.BY_USER_TIME)
        {
            orders=orderService.searchOrderByUserTime(orderSearch);
        }
        else
        {
            return null;
        }

        List<OrderJsonSend> sendOrders=new ArrayList<>();
        for(Order order : orders)
        {
            OrderJsonSend orderJsonSend=new OrderJsonSend(order);
            sendOrders.add(orderJsonSend);
        }
        Gson gson=new Gson();
        System.out.println("order:"+gson.toJson(sendOrders));
        return gson.toJson(sendOrders);
    }
}
