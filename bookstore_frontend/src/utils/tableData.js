import * as bookService from "../services/bookService";

export const convert_book_to_table = (data) => {
    let table = {
        headers: ["ID", "书籍名称", "ISBN", "售价", "作者"],
        line: []
    };

    let num = data.length;
    for (let i = 0; i < num; ++i) {
        let line = [];
        let cur_book = bookService.convert_book_info(data[i]);
        line.push(cur_book.id, cur_book.name, cur_book.IBSN, cur_book.price, cur_book.author);
        table.line.push(line);
    }
    return table;
}

export const convert_user_to_table = (data) => {
    let table = {
        headers: ["ID", "类型", "昵称", "名字", "电话", "地址"],
        line: []
    };

    let num = data.length;
    for (let i = 0; i < num; ++i) {
        let line = [];
        let cur_user = data[i];
        let userId = (cur_user.userId == null) ? "" : cur_user.userId;
        let type = (cur_user.type == null) ? "" : cur_user.type;
        let nickname = (cur_user.nickname == null) ? "" : cur_user.nickname;
        let name = (cur_user.name == null) ? "" : cur_user.name;
        let tel = (cur_user.tel == null) ? "" : cur_user.tel;
        let address = (cur_user.address == null) ? "" : cur_user.address;
        line.push(userId, type, nickname, name, tel, address);
        table.line.push(line);
    }
    console.log(table);
    return table;
}

export const convert_order_to_table = (data) => {
    let table = {
        headers: ["ID", "用户ID", "总价", "创建时间", "商品ID"],
        line: []
    };

    let num = data.length;
    for (let i = 0; i < num; ++i) {
        let line = [];
        let cur_order = data[i];
        let order_id = (cur_order.order_id == null) ? "" : cur_order.order_id;
        let user_id = (cur_order.user_id == null) ? "" : cur_order.user_id;
        let total_price = (cur_order.total_price == null) ? "" : cur_order.total_price;
        let time_stamp = (cur_order.timestamp == null) ? "" : cur_order.timestamp;
        let items_list = cur_order.orderItems;
        let items_len = items_list.length;
        let items = "";
        for (let i = 0; i < items_len; ++i) {
            let item_id = items_list[i].book_id;
            items += item_id + '/';
        }
        line.push(order_id, user_id, total_price, time_stamp, items);
        table.line.push(line);
    }
    console.log(table);
    return table;
}

export const convert_user_consume_to_table = (data) => {
    let table = {
        headers: ["ID", "昵称", "消费总额"],
        line: []
    };

    let num = data.length;
    for (let i = 0; i < num; ++i) {
        let line = [];
        let cur_user = data[i];

        let user = cur_user.userJson;
        let user_id = user.userId;
        let nickname = user.nickname;
        let total_consume = cur_user.total_consume;

        line.push(user_id, nickname, total_consume);
        table.line.push(line);
    }
    console.log(table);
    return table;
}

export const convert_personal_order_to_table = (data) => {
    let table = {
        headers: ["书籍ID", "书名", "数目", "总价"],
        line: []
    };

    let num = data.length;
    for (let i = 0; i < num; ++i) {
        let line = [];
        let cur_stat = data[i];

        let book = cur_stat.book;
        let total_num = cur_stat.total_num;
        let total_price = cur_stat.total_price;
        let book_id = book.bookId;
        let book_name = book.name;

        line.push(book_id, book_name, total_num, total_price);
        table.line.push(line);
    }
    console.log(table);
    return table;
}

export const convert_book_sell_to_table = (data) => {
    let table = {
        headers: ["ID", "销量", "isbn", "书名", "类型", "作者", "价格", "库存"],
        line: []
    };

    let num = data.length;
    for (let i = 0; i < num; ++i) {
        let line = [];
        let book_sell = data[i];

        let book = book_sell.book;
        let book_id = book.bookId;
        let isbn = book.isbn;
        let name = book.name;
        let type = book.type;
        let author = book.author;
        let price = book.price;
        let inventory = book.inventory;
        let sell_num = book_sell.sell_num;

        line.push(book_id, sell_num, isbn, name, type, author, price, inventory);
        table.line.push(line);
    }
    console.log(table);
    return table;
}