package com.sisyphe.bookstore.controller;

import com.sisyphe.bookstore.Json.CartJsonRec;
import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.constant.Operation;
import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.entity.Cart;
import com.sisyphe.bookstore.entity.entityComp.CartId;
import com.sisyphe.bookstore.service.BookService;
import com.sisyphe.bookstore.utils.msgutils.Msg;
import com.sisyphe.bookstore.utils.msgutils.MsgCode;
import com.sisyphe.bookstore.utils.msgutils.MsgUtil;
import com.sisyphe.bookstore.utils.sessionutils.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sisyphe.bookstore.service.CartService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CartController {

    private CartService cartService;
    private BookService bookService;

    @Autowired
    public CartController(CartService cartService, BookService bookService) {
        this.cartService = cartService;
        this.bookService = bookService;
    }

    @RequestMapping(value = "/bookdetail/add_cart", method = RequestMethod.POST)
    public Msg storeCart(@RequestBody Map<String, Integer> params) {
        Integer user_id = SessionUtil.getUserId();
        Integer book_id = params.get(Constant.BOOK_ID);
        Integer piece = params.get(Constant.PIECE);
        System.out.println("cart get:" + user_id + " book_id:" + book_id);
        Cart cart = new Cart(user_id, book_id, piece);
        boolean store = cartService.storeCart(cart);
        Msg msg;
        if (store)
            msg = MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.ADD_CART_ITEM_MSG);
        else
            msg = MsgUtil.makeMsg(MsgCode.ERROR);
        return msg;
    }

    @RequestMapping(value = "/cart")
    public JSONObject getCart() {
        if (!SessionUtil.checkAuth())
            return null;

        System.out.println("call push cart");
        Integer user_id = SessionUtil.getUserId();
        System.out.println("controller user_id:" + user_id);
        List<Cart> carts = cartService.getCart(user_id);

        JSONObject cartJsonSend = new JSONObject();
        List<Integer> cart_piece = new ArrayList<>();
        List<JSONObject> books = new ArrayList<>();

        for (Cart cart : carts) {
            int book_id = cart.get_cart_id().get_book_id();
            int piece = cart.get_piece();
            Book book = bookService.findBookById(book_id);
            books.add(book.getBookJson());
            cart_piece.add(piece);
        }

        cartJsonSend.put("user_id", user_id);
        cartJsonSend.put("cart_piece", cart_piece);
        cartJsonSend.put("books", books);

        return cartJsonSend;
    }

    @RequestMapping(value = "/cart/modify")
    public void modifyCartItem(@RequestBody CartJsonRec cartJsonRec) {
        if (!SessionUtil.checkAuth())
            return;
        int user_id = SessionUtil.getUserId();
        System.out.println("cart modify controller:" + user_id + " " + cartJsonRec.book_id + " " + cartJsonRec.cart_op);
        Operation cart_op = cartJsonRec.cart_op;
        CartId cartId = new CartId(user_id, cartJsonRec.book_id);
        cartService.modifyCartItem(cartId, cart_op);
    }
}
