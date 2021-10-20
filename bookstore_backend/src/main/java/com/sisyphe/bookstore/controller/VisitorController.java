package com.sisyphe.bookstore.controller;

import com.alibaba.fastjson.JSONObject;
import com.sisyphe.bookstore.utils.msgutils.Msg;
import com.sisyphe.bookstore.utils.msgutils.MsgCode;
import com.sisyphe.bookstore.visitor.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitorController {
    @Autowired
    private Visit visit;

    @RequestMapping(value = "/visitor/num", method = RequestMethod.GET)
    public Msg visitorNumber() {
        int val = visit.getCountValue();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("visit", val);
        return new Msg(MsgCode.SUCCESS, jsonObject);
    }
}
