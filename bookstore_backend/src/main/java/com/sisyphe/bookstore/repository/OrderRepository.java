package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.Order;
import com.sisyphe.bookstore.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query(value = "select o from Order o join OrderItem i on o= i.order where o.user_id=:user_id")
    List<Order> searchOrderByUser(@Param("user_id") int user_id);

    @Query("select o from Order o where o.timestamp>=:lower_time and o.timestamp<=:upper_time")
    List<Order> searchOrderByTime(@Param("lower_time") Timestamp lt, @Param("upper_time") Timestamp ut);

    @Query("select o from Order o join OrderItem  i on o=i.order where i.book_id=:bookId")
    List<Order> searchOrderByBookId(@Param("bookId") int bookId);
}
