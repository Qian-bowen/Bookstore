package com.sisyphe.bookstore.service;

import com.sisyphe.bookstore.constant.Operation;
import com.sisyphe.bookstore.entity.Cart;
import com.sisyphe.bookstore.entity.entityId.CartId;

import java.util.List;

public interface CartService {
    boolean storeCart(Cart cart);
    void modifyCartItem(CartId cartId, Operation op);
    List<Cart> getCart(int user_id);
}
