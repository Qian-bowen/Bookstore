package com.sisyphe.bookstore.controller;

import com.sisyphe.bookstore.Json.RegisterJsonRec;
import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.UserAuth;
import com.sisyphe.bookstore.service.UserService;
import com.sisyphe.bookstore.utils.msgutils.Msg;
import com.sisyphe.bookstore.utils.msgutils.MsgUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Msg register(@RequestBody RegisterJsonRec registerJsonRec) {
        User user = new User(registerJsonRec.nickname, registerJsonRec.name, registerJsonRec.tel, registerJsonRec.address, registerJsonRec.email);
        UserAuth userAuth = new UserAuth(registerJsonRec.username, registerJsonRec.password, registerJsonRec.user_type);

        //if register as admin, check the password
        int user_type = registerJsonRec.user_type;
        if (user_type == Constant.ADMIN && !registerJsonRec.admin_valid_pwd.equals(Constant.ADMIN_PWD)) {
            return MsgUtil.makeMsg(Constant.FAIL, "INVALID PASSWORD");
        }

        if (!userService.registerUser(userAuth, user)) {
            return MsgUtil.makeMsg(Constant.FAIL, "USERNAME ALREADY EXIST");
        }
        return MsgUtil.makeMsg(Constant.SUCCESS, "REGISTER SUCCESS");
    }
}
