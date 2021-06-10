package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.entity.entityComp.BookBuyComp;
import com.sisyphe.bookstore.entity.entityComp.UserConsumeComp;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("select o from Order o")
    List<Order> getOrders();

    @Query(value = "select o from Order o join OrderItem i on o= i.order where o.user_id=:user_id")
    List<Order> searchOrderByUser(@Param("user_id") int user_id);

    @Query("select o from Order o where o.timestamp>=:lower_time and o.timestamp<=:upper_time")
    List<Order> searchOrderByTime(@Param("lower_time") Timestamp lt, @Param("upper_time") Timestamp ut);

    @Query("select o from Order o join OrderItem  i on o=i.order where i.book_id=:bookId")
    List<Order> searchOrderByBookId(@Param("bookId") int bookId);

    @Query("select new com.sisyphe.bookstore.entity.entityComp.UserConsumeComp(o.user_id, sum(o.total_price)) from Order o where o.timestamp>=:lower_time and o.timestamp<=:upper_time group by o.user_id order by sum(o.total_price) desc")
    List<UserConsumeComp> userMostConsume(Pageable pageable, @Param("lower_time") Timestamp lt, @Param("upper_time") Timestamp ut);

    @Query("select new com.sisyphe.bookstore.entity.entityComp.BookBuyComp(oi.book_id,sum(oi.piece),sum(oi.price)) from Order o inner join OrderItem oi on o = oi.order where o.user_id = :id and o.timestamp>=:lower_time and o.timestamp<=:upper_time group by oi.book_id")
    List<BookBuyComp> PersonalMostConsume(Pageable pageable, @Param("id") int user_id, @Param("lower_time") Timestamp lt, @Param("upper_time") Timestamp ut);
}
