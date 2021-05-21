package com.sisyphe.bookstore.service;

import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.UserAuth;

public interface UserService {
    UserAuth checkUser(String username,String password);
    boolean registerUser(UserAuth userAuth,User user);
}
