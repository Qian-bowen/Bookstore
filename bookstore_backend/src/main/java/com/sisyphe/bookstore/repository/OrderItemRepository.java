package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.entityComp.BookComp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query("select new com.sisyphe.bookstore.entity.entityComp.BookComp(o.book_id,sum(o.piece)) from OrderItem o where o.timestamp>=:lower_time and o.timestamp<=:upper_time group by o.book_id order by sum(o.piece) desc")
    List<BookComp> bestSellBook(Pageable pageable, @Param("lower_time") Timestamp lt, @Param("upper_time") Timestamp ut);
}
