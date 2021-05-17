package com.sisyphe.bookstore.controller;

import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.utils.Msg;
import com.sisyphe.bookstore.entity.UserAuth;
import com.sisyphe.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import net.sf.json.JSONObject;

import java.util.Map;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * @Description: login
     * @param:
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public JSONObject login(@RequestBody Map<String,String> params){
        String username=params.get(Constant.USERNAME);
        String password=params.get(Constant.PASSWORD);
        System.out.println("hello world");
        System.out.println(username+password);
        UserAuth auth =userService.checkUser(username,password);
        if(auth!=null)
        {
            JSONObject obj=new JSONObject();
            obj.put(Constant.STATUS,Constant.SUCCESS);
            obj.put(Constant.USER_ID,auth.getUserID());
            obj.put(Constant.USERNAME,auth.getUsername());
            obj.put(Constant.USER_TYPE,auth.getUserType());

            JSONObject data=JSONObject.fromObject(auth);
            data.remove(Constant.PASSWORD);

            return obj;
        }
        return null;
    }

}
