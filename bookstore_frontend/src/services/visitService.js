import {getRequest} from "../utils/ajax";
import {prefix} from "../config/config";

export const get_visitor_num = (callback) => {
    const url = prefix+'/visitor/num';
    getRequest(url, callback);
}