import {postFormDataRequest, postRequest} from "../utils/ajax";
import {statisticType} from "../components/constant/Type";
import {prefix} from "../config/config";

export const modifyBook = (data, callback) => {
    const url = prefix+'/manage/admin/book/modify';
    postRequest(url, data, callback);
};

export const delBook = (book_id, callback) => {
    let data = {'book_id': book_id};
    console.log("del:" + book_id);
    const url = prefix+'/manage/admin/book/del';
    postRequest(url, data, callback);
};

export const addBook = (book, callback) => {
    const url = prefix+'/manage/admin/book/add';
    postFormDataRequest(url, book, callback);
};

export const searchBook = (book_search, callback) => {
    const url = prefix+'/book/search';
    postRequest(url, book_search, callback);
};


export const forbidUser = (user_id, callback) => {
    let user_manage = {
        "user_id": user_id,
        "manageType": 0
    };
    const url = prefix+"/admin/manage/user";
    postRequest(url, user_manage, callback);
}

export const permitUser = (user_id, callback) => {
    let user_manage = {
        "user_id": user_id,
        "manageType": 1
    };
    const url = prefix+"/admin/manage/user";
    postRequest(url, user_manage, callback);
}

export const getUsers = (fetch_num, fetch_begin, callback) => {
    let fetch_data = {
        "fetch_num": fetch_num,
        "fetch_begin": fetch_begin,
    };
    const url = prefix+"/admin/manage/get_user";
    postRequest(url, fetch_data, callback);
}

export const statUserByTime = (begin_time, end_time, stat_num, callback) => {
    let stat_data = {
        "type": statisticType.stat_up,
        "statNum": stat_num,
        "lower_time": begin_time,
        "upper_time": end_time,
    };
    const url = prefix+"/statistic/admin/user-consume";
    postRequest(url, stat_data, callback);
}

export const statBookByTime = (begin_time, end_time, stat_num, callback) => {
    let stat_data = {
        "type": statisticType.stat_up,
        "statNum": stat_num,
        "lower_time": begin_time,
        "upper_time": end_time,
    };
    const url = prefix+"/statistic/books";
    postRequest(url, stat_data, callback);
}

export const statPersonalOrder = (begin_time, end_time, stat_num, callback) => {
    let stat_data = {
        "type": statisticType.stat_up,
        "statNum": stat_num,
        "lower_time": begin_time,
        "upper_time": end_time,
    };
    const url = prefix+"/statistic/personal/purchase";
    postRequest(url, stat_data, callback);
}

//line: id name isbn price author
export const impl_backend_book_modify = (book, line) => {
    let tmp_name = line[1];
    let tmp_isbn = line[2];
    let tmp_price = line[3];
    let tmp_author = line[4];

    if (book.name !== tmp_name && tmp_name !== "")
        book.name = tmp_name;
    if (book.isbn !== tmp_isbn && tmp_isbn !== "")
        book.isbn = tmp_isbn;
    if (book.price !== tmp_price && tmp_price !== "")
        book.price = tmp_price;
    if (book.author !== tmp_author && tmp_author !== "")
        book.author = tmp_author;

    return book;
}