package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.dao.CartDao;
import com.sisyphe.bookstore.entity.Cart;
import com.sisyphe.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    CartRepository cartRepository;

    @Override
    public void storeCart(Cart cart)
    {
        System.out.println("dao impl store cart");
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCart(int user_id)
    {
        System.out.println("dao impl push cart");
        List<Cart> cart=cartRepository.getCart();
        return cart;
    }
}
