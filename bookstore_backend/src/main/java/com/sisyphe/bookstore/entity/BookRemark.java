package com.sisyphe.bookstore.entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Document("bookstore")
public class BookRemark {
    @Id
    private String id;
    private Integer bookId;
    private Integer userId;
    private String content;

    public BookRemark(){}

    public BookRemark(Integer bookId,Integer userId,String content)
    {
        this.bookId=bookId;
        this.userId=userId;
        this.content=content;
        this.id=bookId.toString()+'/'+userId.toString()+'/'+ LocalDateTime.now().toString();
    }

    public static JSONObject toJson(BookRemark bookRemark)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("bookId",bookRemark.bookId);
        jsonObject.put("userId",bookRemark.userId);
        jsonObject.put("content",bookRemark.content);
        return jsonObject;
    }


    public static JSONArray toJsonArray(List<BookRemark> bookRemarkList)
    {
        if(bookRemarkList.isEmpty()) return null;
        JSONArray jsonArray=new JSONArray();
        for(BookRemark bookRemark:bookRemarkList)
        {
            jsonArray.add(toJson(bookRemark));
        }
        return jsonArray;
    }


}
