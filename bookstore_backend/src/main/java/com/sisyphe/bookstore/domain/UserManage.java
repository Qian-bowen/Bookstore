package com.sisyphe.bookstore.domain;

import com.sisyphe.bookstore.constant.ManageType;

public class UserManage {
    int user_id;
    ManageType manageType;

    public UserManage(){}
    public UserManage(int user_id,ManageType manageType)
    {
        this.user_id=user_id;
        this.manageType=manageType;
    }

    public int getUser_id(){return user_id;}
    public ManageType getManageType(){return manageType;}
}
