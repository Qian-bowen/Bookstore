import * as bookService from "../services/bookService";

const convert_book_to_table=(data)=>{
    let table={
        headers:["ID","书籍名称","ISBN","售价","作者"],
        line:[]
    };

    let num=data.length;
    for(let i=0;i<num;++i)
    {
        let line=[];
        let cur_book=bookService.convert_book_info(data[i]);
        line.push(cur_book.id,cur_book.name,cur_book.IBSN,cur_book.price,cur_book.author);
        table.line.push(line);
    }
    return table;
}

const convert_user_to_table=(data)=>{
    let table={
        headers:["ID","类型","昵称","名字","电话","地址"],
        line:[]
    };

    let num=data.length;
    for(let i=0;i<num;++i)
    {
        let line=[];
        let cur_user=data[i];
        let userId=(cur_user.userId==null)?"":cur_user.userId;
        let type=(cur_user.type==null)?"":cur_user.type;
        let nickname=(cur_user.nickname==null)?"":cur_user.nickname;
        let name=(cur_user.name==null)?"":cur_user.name;
        let tel=(cur_user.tel==null)?"":cur_user.tel;
        let address=(cur_user.address==null)?"":cur_user.address;
        line.push(userId,type,nickname,name,tel,address);
        table.line.push(line);
    }
    console.log(table);
    return table;
}

const convert_order_to_table=(data)=>{
    let table={
        headers:["ID","用户ID","总价","创建时间","商品ID"],
        line:[]
    };

    let num=data.length;
    for(let i=0;i<num;++i)
    {
        let line=[];
        let cur_order=data[i];
        let order_id=(cur_order.order_id==null)?"":cur_order.order_id;
        let user_id=(cur_order.user_id==null)?"":cur_order.user_id;
        let total_price=(cur_order.total_price==null)?"":cur_order.total_price;
        let time_stamp=(cur_order.timestamp==null)?"":cur_order.timestamp;
        let items_list=cur_order.orderItems;
        let items_len=items_list.length;
        let items="";
        for(let i=0;i<items_len;++i)
        {
            let item_id=items_list[i].book_id;
            items+=item_id+'/';
        }
        line.push(order_id,user_id,total_price,time_stamp,items);
        table.line.push(line);
    }
    console.log(table);
    return table;
}

export{convert_book_to_table,convert_user_to_table,convert_order_to_table}