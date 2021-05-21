package com.sisyphe.bookstore.controller;

import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.entity.UserAuth;
import com.sisyphe.bookstore.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService)
    {
        this.userService=userService;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@RequestBody Map<String,String> params){
        String username=params.get(Constant.USERNAME);
        String password=params.get(Constant.PASSWORD);

        return null;
    }
}
