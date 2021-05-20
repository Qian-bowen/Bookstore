package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.Cart;
import com.sisyphe.bookstore.entity.entityId.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, CartId> {

    @Query("select c from Cart c")
    List<Cart> getCart();

    @Modifying
    @Query("delete from Cart c where c.cartId=:cartId")
    void deleteCartItem(@Param("cartId") CartId cartId);
}
