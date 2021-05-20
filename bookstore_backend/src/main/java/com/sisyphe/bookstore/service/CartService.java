package com.sisyphe.bookstore.service;

import com.sisyphe.bookstore.entity.Cart;

import java.sql.Timestamp;
import java.util.List;

public interface CartService {
    void storeCart(Cart cart);
    List<Cart> getCart(int user_id);
}
