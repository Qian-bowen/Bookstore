package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.entity.Cart;

import java.util.List;

public interface CartDao {
    void storeCart(Cart cart);
    List<Cart> pushCarts(int user_id);
}
