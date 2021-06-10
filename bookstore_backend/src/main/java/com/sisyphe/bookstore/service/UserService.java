package com.sisyphe.bookstore.service;

import com.sisyphe.bookstore.Json.UserJson;
import com.sisyphe.bookstore.domain.UserManage;
import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.UserAuth;

import java.util.List;

public interface UserService {
    UserAuth checkUser(String username,String password);
    boolean registerUser(UserAuth userAuth,User user);
    boolean manageUser(UserManage userManage);
    List<User> getUsers(Integer fetch_num, Integer fetch_begin);
    List<UserJson> getPackUserInfo(Integer fetch_num, Integer fetch_begin);
}
