package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.constant.Operation;
import com.sisyphe.bookstore.dao.CartDao;
import com.sisyphe.bookstore.entity.Cart;
import com.sisyphe.bookstore.entity.entityComp.CartId;
import com.sisyphe.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class CartDaoImpl implements CartDao {

    CartRepository cartRepository;

    @Autowired
    public CartDaoImpl(CartRepository cartRepository)
    {
        this.cartRepository=cartRepository;
    }

    @Override
    public boolean storeCart(Cart cart)
    {
        System.out.println("dao impl store cart");
        cartRepository.save(cart);
        return true;
    }

    @Override
    public List<Cart> getCart(int user_id)
    {
        System.out.println("dao impl push cart");
        List<Cart> cart=cartRepository.getCart();
        return cart;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void modifyCartItem(CartId cartId, Operation op)
    {
        switch(op)
        {
            case ADD:
            {
                Optional<Cart> cart=cartRepository.findById(cartId);
                Cart origin_cart=cart.get();
                origin_cart.add_piece();
                //using save will update
                cartRepository.save(origin_cart);
                System.out.println("add item in cart");
                break;
            }
            case SUB:
            {
                Optional<Cart> cart=cartRepository.findById(cartId);
                Cart origin_cart=cart.get();
                if(origin_cart.get_piece()<=0) return;
                origin_cart.sub_piece();
                cartRepository.save(origin_cart);
                System.out.println("sub item in cart");
                break;
            }
            case DEL:
            {
                cartRepository.deleteById(cartId);
                System.out.println("del item in cart");
                break;
            }
        }

    }
}
