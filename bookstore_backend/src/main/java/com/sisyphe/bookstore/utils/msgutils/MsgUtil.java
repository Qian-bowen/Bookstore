package com.sisyphe.bookstore.utils.msgutils;


import com.alibaba.fastjson.JSONObject;
import com.sisyphe.bookstore.constant.Constant;

public class MsgUtil {

    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    public static final int LOGIN_USER_ERROR = -100;
    public static final int NOT_LOGGED_IN_ERROR = -101;

    public static final String SUCCESS_MSG = "成功！";
    public static final String LOGIN_SUCCESS_MSG = "登录成功！";
    public static final String LOGOUT_SUCCESS_MSG = "登出成功！";
    public static final String LOGOUT_ERR_MSG = "登出异常！";
    public static final String ERROR_MSG = "错误！";
    public static final String LOGIN_USER_ERROR_MSG = "用户名或密码错误，请重新输入！";
    public static final String LOGIN_USER_PROHIBIT_MSG = "您已被管理员禁止登入！";
    public static final String NOT_LOGGED_IN_ERROR_MSG = "登录失效，请重新登录！";

    public static final String ADD_CART_ITEM_MSG = "加入购物车成功！";
    public static final String CHECK_OUT_MSG = "结账成功！";

    public static final String ADMIN_OPERATION_SUCCESS = "管理员操作成功！";
    public static final String ADMIN_OPERATION_FAIL = "管理员操作失败！";
    public static final String ADMIN_NO_AUTH = "您没有管理员权限";


    public static Msg makeMsg(MsgCode code, JSONObject data) {
        return new Msg(code, data);
    }

    public static Msg makeMsg(MsgCode code, String msg, JSONObject data) {
        return new Msg(code, msg, data);
    }

    public static Msg makeMsg(MsgCode code) {
        return new Msg(code);
    }

    public static Msg makeMsg(MsgCode code, String msg) {
        return new Msg(code, msg);
    }

    public static Msg makeMsg(int status, String msg, JSONObject data) {
        return new Msg(status, msg, data);
    }

    public static Msg makeMsg(int status, String msg) {
        return new Msg(status, msg);
    }


}
