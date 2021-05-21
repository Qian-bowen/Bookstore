import {postRequest} from "../utils/ajax";
import {history} from "../utils/history";

export const addCart = (data) => {
    console.log("post in cart");
    const url = 'http://localhost:8080/bookdetail/add_cart';
    const callback = (data) => {
        console.log("add cart");
        if(data.status>=0)
        {
            alert(data.msg);
        }
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

/*
* json: user_id/book_id/cart_op
* */
export const modifyCarts = (data,callback) => {
    const url = 'http://localhost:8080/cart/modify';
    let user_str=localStorage.getItem('user');
    let user=JSON.parse(user_str);
    console.log("cart op:"+data.cart_op);
    data={"user_id":user['userID'],"book_id":data.book_id,"cart_op":data.cart_op};
    postRequest(url, data, callback);
};