package com.sisyphe.bookstore.controller;

import com.google.gson.Gson;
import com.sisyphe.bookstore.Json.UserJson;
import com.sisyphe.bookstore.Json.UserListJsonSend;
import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.domain.UserManage;
import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.service.UserService;
import com.sisyphe.bookstore.utils.msgutils.Msg;
import com.sisyphe.bookstore.utils.msgutils.MsgCode;
import com.sisyphe.bookstore.utils.msgutils.MsgUtil;
import com.sisyphe.bookstore.utils.sessionutils.SessionUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/admin/manage/user")
    public Msg manageUser(@RequestBody UserManage userManage) {
        //check is admin
        if (SessionUtil.getUserType() != Constant.ADMIN) {
            return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.ADMIN_NO_AUTH);
        }

        boolean successManage = userService.manageUser(userManage);
        if (successManage)
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.ADMIN_OPERATION_SUCCESS);
        return MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.ADMIN_OPERATION_FAIL);
    }

    @RequestMapping("/admin/manage/get_user")
    public String getUsers(@RequestBody Map<String, Integer> param) {
        //check is admin
        Msg msg;
        if (SessionUtil.getUserType() != Constant.ADMIN) {
            msg = MsgUtil.makeMsg(MsgCode.ERROR, MsgUtil.ADMIN_NO_AUTH);
        } else {
            msg = MsgUtil.makeMsg(MsgCode.SUCCESS);
        }

        Integer fetch_num = param.get(Constant.FETCH_NUM);
        Integer fetch_begin = param.get(Constant.FETCH_BEGIN);

        List<UserJson> userList = userService.getPackUserInfo(fetch_num, fetch_begin);
        UserListJsonSend userListJsonSend = new UserListJsonSend(msg, userList);
        Gson gson = new Gson();
        return gson.toJson(userListJsonSend);
    }
}
