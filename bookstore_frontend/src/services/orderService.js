import {postRequest} from "../utils/ajax";

export const submitOrder = (data,callback) => {
    console.log("post in submit order");
    const url = 'http://localhost:8080/cart/order';
    let user_str=localStorage.getItem('user');
    let user=JSON.parse(user_str);
    data={"user_id":user['userID'],"total_price":data.total_price,"orderItems":data.orderItems};
    postRequest(url, data, callback);
};

export const getOrders=(fetch_num,fetch_begin,callback)=>{
    const url = 'http://localhost:8080/order/get_orders';
    let data={"fetch_num":fetch_num,"fetch_begin":fetch_begin};
    postRequest(url, data, callback);
}

export const searchOrderByName=(name,callback)=>{
    const url = 'http://localhost:8080/order/search';
    let data={
        "type":0,
        "user_id":null,
        "bookName":name,
        "lower_time":null,
        "upper_time":null
    };
    postRequest(url, data, callback);
}

export const searchOrderByTime=(begin_time,end_time,callback)=>{
    const url = 'http://localhost:8080/order/search';
    let data={
        "type":1,
        "user_id":null,
        "bookName":null,
        "lower_time":begin_time,
        "upper_time":end_time
    };
    postRequest(url, data, callback);
}