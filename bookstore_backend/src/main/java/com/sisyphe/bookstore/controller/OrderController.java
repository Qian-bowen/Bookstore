package com.sisyphe.bookstore.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sisyphe.bookstore.Json.OrderItemJson;
import com.sisyphe.bookstore.Json.OrderJsonRec;
import com.sisyphe.bookstore.Json.OrderJsonSend;
import com.sisyphe.bookstore.Json.OrderMsgWrapper;
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
import com.sisyphe.bookstore.utils.sessionutils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.sisyphe.bookstore.entity.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private KafkaTemplate<String, OrderMsgWrapper> kafkaTemplate;

    /**
     * TODO:price should be get from database
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/cart/order", method = RequestMethod.POST)
    public Msg storeOrder(@RequestBody String body) {
        if (!SessionUtil.checkAuth())
            return new Msg(MsgUtil.ERROR, "please login first");
        int userId = SessionUtil.getUserId();

        Gson gson = new Gson();
        OrderJsonRec orderJsonRec = gson.fromJson(body, OrderJsonRec.class);
        OrderMsgWrapper orderMsgWrapper = new OrderMsgWrapper(userId, orderJsonRec);
        kafkaTemplate.send("order", orderMsgWrapper);

        return new Msg(MsgUtil.SUCCESS, "订单已接收");
    }

    /**
     * TODO:find a proper place for listener
     *
     * @param
     */
    @KafkaListener(topics = "order", containerFactory = "kafkaOrderListenerContainerFactory")
    public void handleOrder(OrderMsgWrapper orderMsgWrapper) {
        Order stored_order = orderService.storeOrder(orderMsgWrapper.getOrderJsonRec(), orderMsgWrapper.getUser_id());

        //remove from cart after make order
        List<OrderItem> items = stored_order.get_items();
        int user_id = stored_order.get_user_id();
        for (OrderItem item : items) {
            CartId cartId = new CartId(user_id, item.get_book_id());
            cartService.modifyCartItem(cartId, Operation.DEL);
        }
    }

    @RequestMapping(value = "/order/get_orders")
    public String getOrders(@RequestBody Map<String, Integer> param) {
        Integer fetch_num = param.get(Constant.FETCH_NUM);
        Integer fetch_begin = param.get(Constant.FETCH_BEGIN);
        List<Order> orderList = orderService.getOrders(fetch_num, fetch_begin);
        System.out.println("orders:" + orderList.size());

        List<OrderJsonSend> sendOrders = new ArrayList<>();
        for (Order order : orderList) {
            OrderJsonSend orderJsonSend = new OrderJsonSend(order);
            sendOrders.add(orderJsonSend);
        }

        Gson gson = new Gson();
        return gson.toJson(sendOrders);
    }

    //TODO CHECK SESSION! EXCEPT ADMIN CANNOT SEARCH OTHERS ORDERS
    @RequestMapping(value = "/order/search")
    public String searchOrder(@RequestBody OrderSearch orderSearch) {
        List<Order> orders;
        if (orderSearch.getType() == SearchType.BY_USER_ID) {
            orders = orderService.searchOrderByUser(orderSearch);
        } else if (orderSearch.getType() == SearchType.BY_TIME) {
            orders = orderService.searchOrderByTime(orderSearch);
        } else if (orderSearch.getType() == SearchType.BY_NAME) {
            orders = orderService.searchOrderByBookName(orderSearch);
        } else if (orderSearch.getType() == SearchType.BY_USER_BOOK) {
            orders = orderService.searchOrderByUserBook(orderSearch);
        } else if (orderSearch.getType() == SearchType.BY_USER_TIME) {
            orders = orderService.searchOrderByUserTime(orderSearch);
        } else {
            return null;
        }

        List<OrderJsonSend> sendOrders = new ArrayList<>();
        for (Order order : orders) {
            OrderJsonSend orderJsonSend = new OrderJsonSend(order);
            sendOrders.add(orderJsonSend);
        }
        Gson gson = new Gson();
        System.out.println("order:" + gson.toJson(sendOrders));
        return gson.toJson(sendOrders);
    }
}
