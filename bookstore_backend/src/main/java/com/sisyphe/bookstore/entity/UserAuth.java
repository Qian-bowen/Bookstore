package com.sisyphe.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user_Auth")
@JsonIgnoreProperties(value={"handler","hibernateLazyInitializer","fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
public class UserAuth {

    @Id
    private Integer userId;
    private String username;

    private String password;

    private Integer userType;

    public UserAuth(String name,String pwd,Integer type)
    {
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
    public void setUserId(Integer userId){this.userId=userId;}
}
