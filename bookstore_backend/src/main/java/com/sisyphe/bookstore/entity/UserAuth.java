package com.sisyphe.bookstore.entity;

public class UserAuth {
    private Integer userId;
    private String username;
    private String password;
    private Integer userType;

    public UserAuth(Integer id,String name,String pwd,Integer type)
    {
        userId=id;
        username=name;
        password=pwd;
        userType=type;
    }

    public UserAuth()
    {
        userId=-1;
    }

    public String getUsername()
    {
        return username;
    }
    public Integer getUserType(){return userType;}
    public Integer getUserID(){return userId;}
}
