package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.dao.OrderDao;
import com.sisyphe.bookstore.domain.OrderItem;
import com.sisyphe.bookstore.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

//TODO:modify JDBC TO ORM
@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Order storeOrder(Order order)
    {
        int user_id=order.get_user_id();
        double total_price=order.get_total_price();
        BigDecimal big_total_price=new BigDecimal(total_price);

        long time = Calendar.getInstance().getTimeInMillis();
        java.sql.Timestamp ts = new java.sql.Timestamp(time);
        System.out.println("create order at:"+ts);

        List<OrderItem> items=order.get_items();


        //insert into order table
        KeyHolder keyHolder=new GeneratedKeyHolder();
        //String sql="INSERT INTO order(order_id,user_id,total_price) VALUES(?,?,?)";
        String sql="INSERT INTO orders(user_id,total_price,created_time) VALUES(?,?,?)";
        jdbcTemplate.update(conn->{
            PreparedStatement ps=conn.prepareStatement(sql,RETURN_GENERATED_KEYS);
            ps.setInt(1,user_id);
            ps.setBigDecimal(2,big_total_price);
            ps.setTimestamp(3,ts);
            return ps;
                },keyHolder);
        int increased_order_Id=keyHolder.getKey().intValue();

        //insert into order_item table
        for(OrderItem orderItem : items)
        {
            int book_id=orderItem.get_book_id();
            double price=orderItem.get_price();
            String item_sql="INSERT INTO orderItem(book_id,order_id,price,created_time) " +
                "VALUES(?,?,?,?)";
            jdbcTemplate.update(item_sql,book_id,increased_order_Id,price,ts);
        }

        order.set_order_id(increased_order_Id);
        order.set_timestamp(ts);

        return order;

    }
}
