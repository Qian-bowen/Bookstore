package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.OrderItem;
import com.sisyphe.bookstore.entity.entityComp.BookSellComp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

    @Query("select new com.sisyphe.bookstore.entity.entityComp.BookSellComp(o.book_id,count(o.book_id)) from OrderItem o group by o.book_id order by count(o.book_id) desc")
    List<BookSellComp> bestSellBook(Pageable pageable);
}
