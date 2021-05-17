package com.sisyphe.bookstore.controller;

import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.entity.UserAuth;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.service.BookService;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value={"/recmd","/manage"})
    @CrossOrigin
    public String getBooks(@RequestBody Map<String,Integer> params) {
        System.out.println(params);
        Integer fetch_num=params.get(Constant.FETCH_NUM);
        Integer fetch_begin=params.get(Constant.FETCH_BEGIN);
        System.out.println("fetch in outer:"+fetch_num+" "+fetch_begin);
        List<Book> books=bookService.getBooks(fetch_num,fetch_begin);
        Gson gson = new Gson();
        // convert list to json
        String book_json = gson.toJson(books);
        System.out.println(book_json);
        return book_json;
    }

    @RequestMapping("/bookdetail")
    @CrossOrigin
    public String getBook(@RequestBody Map<String,Integer> params){
        Integer id=params.get(Constant.BOOK_ID);
        System.out.println("query for book"+id);
        Book book=bookService.findBookById(id);
        Gson gson = new Gson();
        String book_json = gson.toJson(book);
        System.out.println(book_json);
        return book_json;
    }

}
