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
    postRequest(url, data, callback);
};