package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.entity.UserAuth;
import com.sisyphe.bookstore.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserAuth checkUser(String name, String pwd)
    {
        System.out.println("call in dao impl");
        UserAuth result=null;
        String sql="SELECT * FROM user_auth WHERE username=? AND  password=?";
        try{
            result=jdbcTemplate.queryForObject(sql,
                    (rs,rowNum)->new UserAuth(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("user_type")),
                    name,pwd);
            System.out.println("login success");
        }catch(Exception e){
            System.out.println("login fail");
        }
        return result;
    }

    public boolean registerUser(UserAuth userAuth)
    {
        return true;
    }
}
