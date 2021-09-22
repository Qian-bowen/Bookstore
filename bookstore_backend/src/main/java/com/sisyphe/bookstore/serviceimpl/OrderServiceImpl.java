package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.Json.OrderItemJson;
import com.sisyphe.bookstore.Json.OrderJsonRec;
import com.sisyphe.bookstore.constant.SearchType;
import com.sisyphe.bookstore.dao.BookDao;
import com.sisyphe.bookstore.dao.OrderDao;
import com.sisyphe.bookstore.domain.OrderSearch;
import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.service.OrderService;
import com.sisyphe.bookstore.utils.convert.TimeConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private BookDao bookDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao,BookDao bookDao)
    {
        this.orderDao=orderDao;
        this.bookDao=bookDao;
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
            Integer piece=itemJson.piece;
            BigDecimal price=itemJson.price;
            BigDecimal item_total_price=price.multiply(new BigDecimal(piece));
            OrderItem orderItem=new OrderItem(book_id,piece,item_total_price,order);
            items.add(orderItem);

            //reduce inventory book
            if(!bookDao.reduceInventory(book_id,piece))
                return null;
        }

        return orderDao.storeOrder(order,items);
    }

    @Override
    public List<Order> getOrders(Integer fetch_num,Integer fetch_begin)
    {
        return orderDao.getOrders(fetch_num,fetch_begin);
    }

    @Override
    public List<Order> searchOrderByUser(OrderSearch orderSearch)
    {
        return orderDao.searchOrderByUser(orderSearch.getUser_id());
    }

    @Override
    public List<Order> searchOrderByUserBook(OrderSearch orderSearch)
    {
        String bookName=orderSearch.getBookName();
        List<Book> books=bookDao.getBooksByName(bookName);
        if(books==null) return null;
        List<Order> orders=new ArrayList<>();
        for(Book book : books)
        {
            List<Order> tmp_orders=orderDao.searchOrderByUserBook(orderSearch.getUser_id(),book.getBookId());
            orders.addAll(tmp_orders);
        }
        return orders;
    }

    @Override
    public List<Order> searchOrderByUserTime(OrderSearch orderSearch)
    {
        TimeConvert timeConvert=new TimeConvert("yyyy-MM-dd hh:mm:ss","UTC");
        try{
            Timestamp lower_timestamp=timeConvert.StringToTimestamp(orderSearch.getLower_time());
            Timestamp upper_timestamp=timeConvert.StringToTimestamp(orderSearch.getUpper_time());
            return orderDao.searchOrderByUserTime(orderSearch.getUser_id(),lower_timestamp,upper_timestamp);
        }catch(Exception e)
        {

        }
        return null;
    }

    @Override
    public List<Order> searchOrderByTime(OrderSearch orderSearch)
    {
        System.out.println("by time");
        TimeConvert timeConvert=new TimeConvert("yyyy-MM-dd hh:mm:ss","UTC");
        try{
            Timestamp lower_timestamp=timeConvert.StringToTimestamp(orderSearch.getLower_time());
            Timestamp upper_timestamp=timeConvert.StringToTimestamp(orderSearch.getUpper_time());
            return orderDao.searchOrderByTime(lower_timestamp,upper_timestamp);
        }catch(Exception e)
        {

        }
        return null;
    }

    @Override
    public List<Order> searchOrderByBookName(OrderSearch orderSearch)
    {
        String bookName=orderSearch.getBookName();
        List<Book> books=bookDao.getBooksByName(bookName);
        if(books==null) return null;
        List<Order> orders=new ArrayList<>();
        for(Book book : books)
        {
            List<Order> tmp_orders=orderDao.searchOrderByBookId(book.getBookId());
            orders.addAll(tmp_orders);
        }
        return orders;
    }
}
