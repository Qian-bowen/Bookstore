import {postRequest} from "../utils/ajax";
import React from "react";
import {history} from "../utils/history";

export const login = (data) => {
    console.log("post in login:"+data);
    const url = 'http://localhost:8080/login';
    const callback = (data) => {
        console.log("back:"+data.username);
        console.log("back:"+data.status);
        if(data.status>=0)
        {
            localStorage.setItem('user_id',data.user_id);
            history.push("/");
        }
    };
    postRequest(url, data, callback);
};
