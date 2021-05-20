import {postRequest} from "../utils/ajax";
import React from "react";
import {history} from "../utils/history";
//import config from 'config';

export const login = (data) => {
    console.log("post in login:"+data);
    const url = 'http://localhost:8080/login';
    const callback = (data) => {
        console.log("back:"+data.username);
        console.log("back:"+data.status);
        if(data.status>=0)
        {
            localStorage.setItem('user',JSON.stringify(data.data));
            history.push("/");
            alert("LOGIN SUCCESS");
        }
        else
        {
            alert("LOGIN FAIL");
        }
    };
    postRequest(url, data, callback);
};

export const checkSession=(callback)=>{
    const url = 'http://localhost:8080/checkSession';
    postRequest(url, {}, callback);
};
