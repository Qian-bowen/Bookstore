package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
