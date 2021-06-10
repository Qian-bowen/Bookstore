package com.sisyphe.bookstore.entity.entityComp;

import java.math.BigDecimal;

public class UserConsumeComp {
    private int userId;
    private BigDecimal total_consume;
    public UserConsumeComp(){}
    public UserConsumeComp(int userId, BigDecimal total_consume)
    {
        this.userId=userId;
        this.total_consume=total_consume;
    }

    public int getUserId(){return userId;}
    public BigDecimal getTotal_consume(){return total_consume;}
}
