package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.entity.UserAuth;
import com.sisyphe.bookstore.dao.UserDao;

import com.sisyphe.bookstore.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    UserAuthRepository userAuthRepository;

    public UserAuth checkUser(String name, String pwd)
    {
        return userAuthRepository.checkUser(name,pwd);
    }

    public boolean registerUser(UserAuth userAuth)
    {
        return true;
    }
}
