package com.sisyphe.bookstore.interceptor;


import com.sisyphe.bookstore.utils.msgutils.Msg;
import com.sisyphe.bookstore.utils.msgutils.MsgCode;
import com.sisyphe.bookstore.utils.msgutils.MsgUtil;
import com.sisyphe.bookstore.utils.sessionutils.SessionUtil;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionValidateInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {

        boolean status = SessionUtil.checkAuth();
        if (!status) {
            System.out.println("Failed");
            Msg msg = MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
            sendJsonBack(response, msg);
            return false;
        }
        System.out.println("Success");
        return true;
    }

    private void sendJsonBack(HttpServletResponse response, Msg msg) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(JSONObject.fromObject(msg));
        } catch (IOException e) {
            System.out.println("send json back error");
        }
    }

}