package com.sisyphe.bookstore.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    @Id
    private int userId;
    private String nickname;
    private String name;
    private String tel;
    private String address;
}
