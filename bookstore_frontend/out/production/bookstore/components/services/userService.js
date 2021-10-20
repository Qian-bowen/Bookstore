import {postRequest} from "../utils/ajax";

export const login = (data) => {
    const url = `http://localhost:8080/login`;
    const callback = (data) => {

    };
    postRequest(url, data, callback);
};