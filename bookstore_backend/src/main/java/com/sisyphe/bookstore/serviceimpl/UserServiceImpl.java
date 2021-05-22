package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.dao.UserDao;

import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.UserAuth;
import com.sisyphe.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao)
    {
        this.userDao=userDao;
    }

    @Override
    public UserAuth checkUser(String username, String password)
    {
        return userDao.checkUser(username, password);
    }

    @Override
    public boolean registerUser(UserAuth userAuth,User user){return userDao.registerUser(userAuth,user);}
}
