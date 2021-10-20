import {postRequest} from "../utils/ajax";
import React from "react";
import {history} from "../utils/history";

export const login = (data) => {
    const url = 'http://localhost:8080/login';
    const callback = (data) => {
        console.log("back:" + data.username);
        console.log("back:" + data.status);
        if (data.status >= 0) {
            localStorage.setItem('user', JSON.stringify(data.data));
            history.push("/");
            alert("LOGIN SUCCESS");
        } else {
            alert("LOGIN FAIL");
        }
    };
    postRequest(url, data, callback);
};

export const logout = () => {
    const url = 'http://localhost:8080/logout';
    const callback = (data) => {
        if (data.status >= 0) {
            localStorage.removeItem('user');
            history.push("/");
            alert("LOGOUT SUCCESS");
        } else {
            alert("LOGOUT FAIL");
        }
    };
    postRequest(url, {}, callback);
}

export const register = (data) => {
    console.log("post in login:" + data);
    const url = 'http://localhost:8080/register';
    const callback = (data) => {
        if (data.status >= 0) {
            history.push("/login");
            alert(data.msg);
        } else {
            alert(data.msg);
        }
    };
    postRequest(url, data, callback);
};

export const checkSession = (callback) => {
    const url = 'http://localhost:8080/checkSession';
    postRequest(url, {}, callback);
};
