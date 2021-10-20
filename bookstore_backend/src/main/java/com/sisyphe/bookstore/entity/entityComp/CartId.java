package com.sisyphe.bookstore.entity.entityComp;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartId implements Serializable {

    private int user_id;
    private int book_id;

    public CartId() {
    }

    public CartId(int user_id, int book_id) {
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public int get_user_id() {
        return user_id;
    }

    public void set_user_id(int uid) {
        user_id = uid;
    }

    public int get_book_id() {
        return book_id;
    }

    public void set_book_id(int bid) {
        book_id = bid;
    }

}
