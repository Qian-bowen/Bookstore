import {postRequest} from "../utils/ajax";

export const submitOrder = (data,callback) => {
    console.log("post in submit order");
    const url = 'http://localhost:8080/cart/order';
    postRequest(url, data, callback);
};