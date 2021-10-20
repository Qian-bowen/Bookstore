package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.Json.UserJson;
import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.constant.ManageType;
import com.sisyphe.bookstore.dao.UserDao;

import com.sisyphe.bookstore.domain.UserManage;
import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.UserAuth;
import com.sisyphe.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserAuth checkUser(String username, String password) {
        return userDao.checkUser(username, password);
    }

    @Override
    public boolean registerUser(UserAuth userAuth, User user) {
        return userDao.registerUser(userAuth, user);
    }

    @Override
    public boolean manageUser(UserManage userManage) {
        ManageType manageType = userManage.getManageType();
        Integer manageId = userManage.getUser_id();

        //check if operate on admin, because you cannot ban admin
        Integer userType = userDao.getUserType(manageId);
        if (userType == Constant.ADMIN)
            return false;

        if (manageType == ManageType.BAN)
            return userDao.prohibitUser(manageId);
        else if (manageType == ManageType.PERMIT)
            return userDao.permitUser(manageId);
        return false;
    }

    @Override
    public List<User> getUsers(Integer fetch_num, Integer fetch_begin) {
        return userDao.getUsers(fetch_num, fetch_begin);
    }

    @Override
    public List<UserJson> getPackUserInfo(Integer fetch_num, Integer fetch_begin) {
        List<User> userList = userDao.getUsers(fetch_num, fetch_begin);
        List<UserJson> userJsonList = new ArrayList<>();
        for (User user : userList) {
            Integer type = userDao.getUserType(user.getUserId());
            UserJson userJson = new UserJson(user, type);
            userJsonList.add(userJson);
        }
        return userJsonList;
    }
}
