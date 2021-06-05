package com.sisyphe.bookstore.domain;

import com.sisyphe.bookstore.constant.SearchType;

public class OrderSearch {
    SearchType type;
    int user_id;
    String bookName;
    String lower_time;
    String upper_time;

    public OrderSearch(){}
    public OrderSearch(SearchType searchType,int uid)
    {
        type=searchType;
        user_id=uid;
    }

    public OrderSearch(SearchType searchType,String name)
    {
        type=searchType;
        bookName=name;
    }

    public OrderSearch(SearchType searchType,String lt,String upt)
    {
        type=searchType;
        lower_time=lt;
        upper_time=upt;
    }

    public int getUser_id() {
        return user_id;
    }

    public SearchType getType() {
        return type;
    }

    public String getLower_time(){return lower_time;}

    public String getUpper_time(){return upper_time;}

    public String getBookName(){return bookName;}
}