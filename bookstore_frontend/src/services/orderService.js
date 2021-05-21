import {postRequest} from "../utils/ajax";

export const submitOrder = (data,callback) => {
    console.log("post in submit order");
    const url = 'http://localhost:8080/cart/order';
    let user_str=localStorage.getItem('user');
    let user=JSON.parse(user_str);
    data={"user_id":user['userID'],"total_price":data.total_price,"orderItems":data.orderItems};
    postRequest(url, data, callback);
};