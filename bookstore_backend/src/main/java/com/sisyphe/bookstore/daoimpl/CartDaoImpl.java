package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.dao.CartDao;
import com.sisyphe.bookstore.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void storeCart(Cart cart)
    {
        int user_id= cart.get_user_id();
        int book_id=cart.get_book_id();
        int piece=cart.get_piece();

        String sql="REPLACE INTO cartItem(user_id,book_id,piece) VALUES(?,?,?)";
        jdbcTemplate.update(sql,user_id,book_id,piece);

    }

    @Override
    public List<Cart> pushCarts(int user_id)
    {
        System.out.println("dao impl push cart");
        List<Cart> carts;
        String sql="SELECT * FROM cartitem WHERE user_id="+user_id;
        carts=jdbcTemplate.query(sql,
                (rs,rowNum)->new Cart(
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getInt("piece")
                )
        );
        return carts;

    }
}
