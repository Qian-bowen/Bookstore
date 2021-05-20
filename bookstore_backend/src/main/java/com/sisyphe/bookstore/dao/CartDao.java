package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.constant.Operation;
import com.sisyphe.bookstore.entity.Cart;
import com.sisyphe.bookstore.entity.entityId.CartId;

import java.util.List;

public interface CartDao {
    void storeCart(Cart cart);
    void modifyCartItem(CartId cartId, Operation op);
    List<Cart> getCart(int user_id);
}
