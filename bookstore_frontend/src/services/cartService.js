import {postRequest} from "../utils/ajax";
import {history} from "../utils/history";

export const addCart = (data) => {
    console.log("post in cart");
    const url = 'http://localhost:8080/bookdetail/add_cart';
    const callback = (data) => {
        console.log("add cart");
    };
    postRequest(url, data, callback);
};

export const getCarts = (data,callback) => {
    const url = 'http://localhost:8080/cart';
    let user_str=localStorage.getItem('user');
    let user=JSON.parse(user_str);
    data={"user_id":user['userID']};
    postRequest(url, data, callback);
};