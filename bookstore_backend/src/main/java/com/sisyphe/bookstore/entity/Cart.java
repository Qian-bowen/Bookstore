package com.sisyphe.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sisyphe.bookstore.entity.entityComp.CartId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="cartitem")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "cartId")
public class Cart implements Serializable {
    @EmbeddedId
    private CartId cartId;
    private int piece;

    public Cart(){}
    public Cart(int uid,int bid,int p)
    {
        cartId=new CartId(uid,bid);
        piece=p;
    }
    public CartId get_cart_id(){return cartId;}

    public int get_piece(){return piece;}
    public void add_piece(){piece++;}
    public void sub_piece()
    {
        if(piece>0)
        {
            piece--;
        }
    }

}
