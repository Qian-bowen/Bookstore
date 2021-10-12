import {getRequest, postRequest} from "../utils/ajax";

export const getBooks = (data,callback) => {
    console.log("post in book service");
    const url = 'http://localhost:8080/recmd';
    postRequest(url, data, callback);
};

export const getBook = (id, callback) => {
    const data = {"book_id": id};
    console.log("id:"+id);
    const url = 'http://localhost:8080/bookdetail';
    postRequest(url, data, callback);

};

export const searchBookIntro = (searchStr, callback) => {
    const url = 'http://localhost:8080/search/book/intro?searchStr='+searchStr;
    getRequest(url, callback);

};

/*
* convert book info from backend to frontend
* */
export const convert_book_info=(back_info)=>
{
    let book_info={
        id:back_info.bookId,
        name:back_info.name,
        price:back_info.price,
        tag:[back_info.type,"测试标签"],
        img:back_info.image,
        fin_score:8.2,
        score:[10,50,15,15,10],
        IBSN:back_info.isbn,
        author:back_info.author,
        press:"",
        year:"",
        brief_intro: "",
        spec_intro:back_info.description
    }
    return book_info;
}