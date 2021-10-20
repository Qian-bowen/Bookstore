package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.UserAuth;

import java.util.List;

public interface UserDao {
    UserAuth checkUser(String username, String password);

    boolean registerUser(UserAuth userAuth, User user);

    boolean prohibitUser(int user_id);

    boolean permitUser(int user_id);

    User findUserById(int userId);

    List<User> getUsers(Integer fetch_num, Integer fetch_begin);

    Integer getUserType(Integer user_id);
}
