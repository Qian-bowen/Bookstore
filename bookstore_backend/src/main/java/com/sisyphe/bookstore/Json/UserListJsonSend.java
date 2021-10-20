package com.sisyphe.bookstore.Json;

import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.utils.msgutils.Msg;
import javassist.compiler.ast.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserListJsonSend {
    Msg msg;
    List<UserJson> userJsonList;

    public UserListJsonSend() {
    }

    public UserListJsonSend(Msg msg, List<UserJson> userJsonList) {
        this.userJsonList = userJsonList;
        this.msg = msg;
    }
}
