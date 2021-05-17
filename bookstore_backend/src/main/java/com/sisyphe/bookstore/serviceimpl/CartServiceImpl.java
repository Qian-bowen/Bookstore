package com.sisyphe.bookstore.serviceimpl;
import com.sisyphe.bookstore.dao.CartDao;
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
    public void storeCart(Cart cart)
    {
        cartDao.storeCart(cart);
    }

    @Override
    public List<Cart> pushCarts(int user_id)
    {
        return cartDao.pushCarts(user_id);
    }

}
