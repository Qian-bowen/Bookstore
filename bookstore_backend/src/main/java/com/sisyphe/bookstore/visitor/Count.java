package com.sisyphe.bookstore.visitor;

import java.util.concurrent.atomic.AtomicInteger;

public class Count {
    private AtomicInteger c=new AtomicInteger(0);
    public synchronized int incrementAndGet(){return c.incrementAndGet();}
    public synchronized int getCount(){return c.get();}

    public void setValue(int val)
    {
        c.getAndSet(val);
    }

    public Count(){}
}
