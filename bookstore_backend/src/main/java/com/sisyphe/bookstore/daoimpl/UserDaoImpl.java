package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.UserAuth;
import com.sisyphe.bookstore.dao.UserDao;

import com.sisyphe.bookstore.repository.UserAuthRepository;
import com.sisyphe.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    UserAuthRepository userAuthRepository;
    UserRepository userRepository;

    @Autowired
    public UserDaoImpl(UserAuthRepository userAuthRepository,UserRepository userRepository)
    {
        this.userAuthRepository=userAuthRepository;
        this.userRepository=userRepository;
    }

    public UserAuth checkUser(String name, String pwd)
    {
        return userAuthRepository.checkUser(name,pwd);
    }

    public boolean registerUser(UserAuth userAuth, User user)
    {
        userRepository.save(user);
        return true;
    }
}
