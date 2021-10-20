package com.sisyphe.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String nickname;
    private String name;
    private String tel;
    private String address;
    private String email;

    public User() {
    }

    public User(String nickname, String name, String tel, String address, String email) {
        this.nickname = nickname;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.email = email;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getName() {
        return this.tel;
    }

    public String getTel() {
        return this.tel;
    }

    public String getAddress() {
        return this.address;
    }

    public String getEmail() {
        return this.email;
    }
}
