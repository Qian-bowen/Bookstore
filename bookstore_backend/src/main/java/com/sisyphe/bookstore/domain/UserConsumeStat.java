package com.sisyphe.bookstore.domain;

import com.sisyphe.bookstore.Json.UserJson;
import com.sisyphe.bookstore.entity.User;

import java.math.BigDecimal;

public class UserConsumeStat {
    private UserJson userJson;
    private BigDecimal total_consume;
    public UserConsumeStat(){}
    public UserConsumeStat(UserJson userJson, BigDecimal total_consume)
    {
        this.userJson=userJson;
        this.total_consume=total_consume;
    }

    public UserJson getUser(){return userJson;}
    public BigDecimal getTotal_consume(){return total_consume;}
}
