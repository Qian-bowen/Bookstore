import {getRequest} from "../utils/ajax";

export const get_visitor_num=(callback)=>{
    const url = 'http://localhost:8080/visitor/num';
    getRequest(url,callback);
}