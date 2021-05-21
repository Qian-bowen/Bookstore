package com.sisyphe.bookstore.serviceimpl;
import com.sisyphe.bookstore.constant.Operation;
import com.sisyphe.bookstore.dao.CartDao;
import com.sisyphe.bookstore.entity.entityId.CartId;
import com.sisyphe.bookstore.service.CartService;
import com.sisyphe.bookstore.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public boolean storeCart(Cart cart)
    {
        return cartDao.storeCart(cart);
    }

    @Override
    public void modifyCartItem(CartId cartId, Operation op)
    {
        cartDao.modifyCartItem(cartId,op);
    }

    @Override
    public List<Cart> getCart(int user_id)
    {
        return cartDao.getCart(user_id);
    }

}
