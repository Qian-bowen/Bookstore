package com.sisyphe.bookstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.entity.UserAuth;
import com.sisyphe.bookstore.service.UserService;
import com.sisyphe.bookstore.utils.msgutils.Msg;
import com.sisyphe.bookstore.utils.msgutils.MsgCode;
import com.sisyphe.bookstore.utils.msgutils.MsgUtil;
import com.sisyphe.bookstore.utils.sessionutils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import net.sf.json.JSONObject;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@RestController
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService)
    {
        this.userService=userService;
    }

    @RequestMapping("/test")
    public String getFromUrl() {
        System.out.println("hello world");
        final String url = "http://localhost:6800/schedule.json";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("project", "fund");
        requestBody.add("spider", "company");

        HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.POST, formEntity, String.class);
        return "";
    }
    
    @RequestMapping(value = "/login")
    public Msg login(@RequestBody Map<String,String> params){
        String username=params.get(Constant.USERNAME);
        String password=params.get(Constant.PASSWORD);
        System.out.println(username+password);
        UserAuth auth =userService.checkUser(username,password);
        if(auth!=null && auth.getUserType()!=Constant.BANNED_USER)
        {
            JSONObject obj=new JSONObject();
            obj.put(Constant.USER_ID,auth.getUserID());
            obj.put(Constant.USERNAME,auth.getUsername());
            obj.put(Constant.USER_TYPE,auth.getUserType());
            SessionUtil.setSession(obj);

            JSONObject data=JSONObject.fromObject(auth);
            data.remove(Constant.PASSWORD);
            Msg msg= MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, data);
            System.out.println("login status:"+msg.getStatus()+" "+msg.getMsg());
            return msg;
        }
        else if(auth.getUserType()==Constant.BANNED_USER)
        {
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR,MsgUtil.LOGIN_USER_PROHIBIT_MSG);
        }
        else
        {
            return MsgUtil.makeMsg(MsgCode.LOGIN_USER_ERROR);
        }
    }

    @RequestMapping("/logout")
    public Msg logout()
    {
        if(SessionUtil.removeSession())
        {
            return MsgUtil.makeMsg(MsgCode.SUCCESS);
        }
        else
        {
            return MsgUtil.makeMsg(MsgCode.ERROR);
        }
    }

    @RequestMapping("/checkSession")
    public Msg checkSession(){
        JSONObject auth = SessionUtil.getAuth();

        if(auth == null){
            return MsgUtil.makeMsg(MsgCode.NOT_LOGGED_IN_ERROR);
        }
        else{
            return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.LOGIN_SUCCESS_MSG, auth);
        }
    }

}
