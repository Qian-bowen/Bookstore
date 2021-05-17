package com.sisyphe.bookstore.utils;

import net.sf.json.JSONObject;

public class Msg {
    private int status;
    private String message;
    private JSONObject data;

    public Msg(int stat,String msg,JSONObject object)
    {
        this.status=stat;
        this.message=msg;
        this.data=object;
    }

    public int get_status(){return status;}

}
