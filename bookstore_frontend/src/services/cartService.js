import {postRequest} from "../utils/ajax";
import {history} from "../utils/history";
import {prefix} from "../config/config";

export const addCart = (data) => {
    console.log("post in cart");
    const url = prefix+"/bookdetail/add_cart";
    const callback = (data) => {
        console.log("add cart");
        if (data.status >= 0) {
            alert(data.msg);
        }
    };
    postRequest(url, data, callback);
};

export const getCarts = (data, callback) => {
    const url = prefix+'/cart';
    data = {};
    postRequest(url, data, callback);
};

/*
* json: user_id/book_id/cart_op
* */
export const modifyCarts = (data, callback) => {
    const url = prefix+'/cart/modify';
    console.log("cart op:" + data.cart_op);
    data = {"book_id": data.book_id, "cart_op": data.cart_op};
    postRequest(url, data, callback);
};