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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Transactional(propagation = Propagation.REQUIRED)
    public Order storeOrder(OrderJsonRec orderJsonRec,int user_id){

        List<OrderItemJson> orderItems=orderJsonRec.orderItems;

        BigDecimal total_price=BigDecimal.valueOf(0);
        List<OrderItem> items=new ArrayList<>();

        for(OrderItemJson itemJson:orderItems)
        {
            int book_id=itemJson.book_id;
            int piece=itemJson.piece;
            Book book=bookDao.findOne(book_id);
            if(book==null) continue;
            BigDecimal price=book.getPrice();
            BigDecimal item_total_price=price.multiply(new BigDecimal(piece));
            total_price=total_price.add(item_total_price);
            OrderItem orderItem=new OrderItem(book_id,piece,item_total_price);
            items.add(orderItem);

            //reduce inventory book
            if(!bookDao.reduceInventory(book_id,piece))
                return null;
        }

        Order order=new Order(user_id,total_price);
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
