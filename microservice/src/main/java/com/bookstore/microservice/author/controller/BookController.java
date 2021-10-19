package com.bookstore.microservice.author.controller;

import com.alibaba.fastjson.JSONObject;
import com.bookstore.microservice.author.entity.Book;
import com.bookstore.microservice.author.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/authorOfBook")
    public JSONObject getAuthorOfBook(@RequestParam("name") String name)
    {
        List<Book> bookList=bookRepository.getBooksByName(name);
        JSONObject jsonObject=new JSONObject();
        if(bookList.isEmpty())
        {
            jsonObject.put("author","");
        }
        else
        {
            jsonObject.put("author",bookList.get(0).getAuthor());
        }
        return jsonObject;
    }
}
