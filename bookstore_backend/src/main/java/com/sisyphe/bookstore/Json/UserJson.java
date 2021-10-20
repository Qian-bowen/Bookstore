package com.sisyphe.bookstore.Json;

import com.sisyphe.bookstore.entity.User;

public class UserJson {
    public int userId;
    public String nickname;
    public String name;
    public String tel;
    public String address;
    public Integer type; //if type is unnecessary type=-1

    public UserJson(User user, Integer type) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.name = user.getName();
        this.tel = user.getTel();
        this.address = user.getAddress();
        this.type = type;
    }
}
