package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.Cart;
import com.sisyphe.bookstore.entity.entityComp.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, CartId> {

    @Query("select c from Cart c")
    List<Cart> getCart();
}
