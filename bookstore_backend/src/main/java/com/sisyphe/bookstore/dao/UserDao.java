package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.UserAuth;

public interface UserDao {
    UserAuth checkUser(String username, String password);
    boolean registerUser(UserAuth userAuth, User user);
}
