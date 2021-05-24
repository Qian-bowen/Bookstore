package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
