package com.sisyphe.bookstore.Json;

import lombok.Data;

@Data
public class OrderMsgWrapper {
    int user_id;
    OrderJsonRec orderJsonRec;

    public OrderMsgWrapper() {
    }

    public OrderMsgWrapper(int user_id, OrderJsonRec orderJsonRec) {
        this.user_id = user_id;
        this.orderJsonRec = orderJsonRec;
    }
}
