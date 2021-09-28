import {postRequest} from "../utils/ajax";
import * as Type from "../components/constant/Type";

export const submitOrder = (data,callback) => {
    console.log("post in submit order");
    const url = 'http://localhost:8080/cart/order';
    data={"orderItems":data.orderItems};
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

export const searchPersonalOrder=(begin_time,end_time,callback)=>{
    const url = 'http://localhost:8080/order/search';
    let user=JSON.parse(localStorage.getItem('user'));
    console.log("user_id:"+user.userID+" type:"+Type.searchByType.by_user_id);
    let data={
        "type":Type.searchByType.by_user_id,
        "user_id":user.userID,
        "bookName":null,
        "lower_time":null,
        "upper_time":null
    };
    postRequest(url, data, callback);
}

export const searchPersonalOrderByBook=(book_name,callback)=>{
    const url = 'http://localhost:8080/order/search';
    let user=JSON.parse(localStorage.getItem('user'));
    console.log("user_id:"+user.userID+" type:"+Type.searchByType.by_user_id);
    let data={
        "type":Type.searchByType.by_user_book,
        "user_id":user.userID,
        "bookName":book_name,
        "lower_time":null,
        "upper_time":null
    };
    postRequest(url, data, callback);
}

export const searchPersonalOrderByTime=(begin_time,end_time,callback)=>{
    const url = 'http://localhost:8080/order/search';
    let user=JSON.parse(localStorage.getItem('user'));
    console.log("user_id:"+user.userID+" type:"+Type.searchByType.by_user_id);
    let data={
        "type":Type.searchByType.by_user_time,
        "user_id":user.userID,
        "bookName":null,
        "lower_time":begin_time,
        "upper_time":end_time
    };
    postRequest(url, data, callback);
}