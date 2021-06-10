package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.UserAuth;
import com.sisyphe.bookstore.dao.UserDao;

import com.sisyphe.bookstore.repository.UserAuthRepository;
import com.sisyphe.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public UserAuth checkUser(String name, String pwd)
    {
        return userAuthRepository.checkUser(name,pwd);
    }

    @Override
    public boolean registerUser(UserAuth userAuth, User user)
    {
        //check whether there is already exist same username
        UserAuth dup=userAuthRepository.checkUsernameExist(userAuth.getUsername());
        if(dup!=null)
        {
            return false;
        }
        User user_saved=userRepository.saveAndFlush(user);
        int user_id=user_saved.getUserId();
        System.out.println("user_id:"+user_id);
        userAuth.setUserId(user_id);
        userAuthRepository.save(userAuth);
        return true;
    }

    @Override
    public boolean prohibitUser(int user_id)
    {
        boolean user_exist=userAuthRepository.existsById(user_id);
        if(user_exist)
        {
            UserAuth userAuth=userAuthRepository.getOne(user_id);
            userAuth.setUserType(Constant.BANNED_USER);
            userAuthRepository.save(userAuth);
            return true;
        }
        return false;
    }

    @Override
    public boolean permitUser(int user_id)
    {
        boolean user_exist=userAuthRepository.existsById(user_id);
        if(user_exist)
        {
            UserAuth userAuth=userAuthRepository.getOne(user_id);
            userAuth.setUserType(Constant.USER);
            userAuthRepository.save(userAuth);
            return true;
        }
        return false;
    }

    @Override
    public User findUserById(int userId)
    {
        return userRepository.getOne(userId);
    }

    @Override
    public List<User> getUsers(Integer fetch_num, Integer fetch_begin)
    {
        return userRepository.findAll();
    }

    @Override
    public Integer getUserType(Integer user_id)
    {
        UserAuth userAuth = userAuthRepository.getOne(user_id);
        return userAuth.getUserType();
    }

}
