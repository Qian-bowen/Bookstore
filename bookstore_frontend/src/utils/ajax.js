
let postRequest=(url,json,callback)=>
{
    console.log("post:"+json);
    let opts={
        method:"POST",
        body:JSON.stringify(json),
        headers:{
            'Content-Type': 'application/json'
        },
        credentials: "include"
    };

    fetch(url,opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        });
}

let postFormDataRequest=(url,data,callback)=>
{
    let opts={
        method:"POST",
        body:data,
        credentials: "include"
    };

    fetch(url,opts)
        .then((response) => {
            return response.json()
        })
        .then((data) => {
            callback(data);
        })
        .catch((error) => {
            console.log(error);
        });
}

export {postRequest,postFormDataRequest};