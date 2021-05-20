package com.sisyphe.bookstore.controller;

import com.google.gson.Gson;
import com.sisyphe.bookstore.Json.CartJsonRec;
import com.sisyphe.bookstore.Json.CartJsonSend;
import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.constant.Operation;
import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.entity.Cart;
import com.sisyphe.bookstore.entity.entityId.CartId;
import com.sisyphe.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sisyphe.bookstore.service.CartService;

import java.util.List;
import java.util.Map;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;

    @RequestMapping(value="/bookdetail/add_cart",method = RequestMethod.POST)
    public void storeCart(@RequestBody Map<String,Integer> params)
    {
        Integer user_id=params.get(Constant.USER_ID);
        Integer book_id=params.get(Constant.BOOK_ID);
        Integer piece=params.get(Constant.PIECE);
        System.out.println("cart get:"+user_id+" book_id:"+book_id);
        Cart cart=new Cart(user_id,book_id,piece);
        cartService.storeCart(cart);
    }

    @RequestMapping(value="/cart")
    public String getCart(@RequestBody Map<String,Integer> params)
    {
        System.out.println("call push cart");
        Integer user_id=params.get(Constant.USER_ID);
        System.out.println("controller user_id:"+user_id);
        List<Cart> carts=cartService.getCart(user_id);

        CartJsonSend cartJsonSend=new CartJsonSend();
        cartJsonSend.user_id=user_id;

        for(Cart cart : carts)
        {
            int book_id=cart.get_cart_id().get_book_id();
            int piece=cart.get_piece();
            Book book= bookService.findBookById(book_id);
            cartJsonSend.books.add(book);
            cartJsonSend.cart_piece.add(piece);
            System.out.println("book_id:"+book_id);
        }

        Gson gson = new Gson();
        // convert list to json
        String cart_json = gson.toJson(cartJsonSend);
        return cart_json;
    }

    @RequestMapping(value="/cart/modify")
    public void modifyCartItem(@RequestBody CartJsonRec cartJsonRec)
    {
        System.out.println("cart modify controller:"+cartJsonRec.user_id+" "+cartJsonRec.book_id+" "+cartJsonRec.cart_op);
        Operation cart_op=cartJsonRec.cart_op;
        CartId cartId=new CartId(cartJsonRec.user_id,cartJsonRec.book_id);
        cartService.modifyCartItem(cartId,cart_op);
    }
}
