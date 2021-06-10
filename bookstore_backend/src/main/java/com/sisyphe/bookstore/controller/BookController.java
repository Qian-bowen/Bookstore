package com.sisyphe.bookstore.controller;

import com.sisyphe.bookstore.Json.BookJson;
import com.sisyphe.bookstore.constant.Constant;
import com.sisyphe.bookstore.domain.BookSearch;
import com.sisyphe.bookstore.utils.msgutils.Msg;
import com.sisyphe.bookstore.utils.msgutils.MsgCode;
import com.sisyphe.bookstore.utils.msgutils.MsgUtil;
import com.sisyphe.bookstore.utils.sessionutils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.service.BookService;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService)
    {
        this.bookService=bookService;
    }

    @RequestMapping(value={"/recmd","/manage"})
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

    @RequestMapping("/book/search")
    public String searchBooks(@RequestBody BookSearch bookSearch) {
        List<Book> books=bookService.searchBooks(bookSearch);
        Gson gson = new Gson();
        String book_json = gson.toJson(books);
        return book_json;
    }

    @RequestMapping("/bookdetail")
    public String getBook(@RequestBody Map<String,Integer> params){
        Integer id=params.get(Constant.BOOK_ID);
        System.out.println("query for book"+id);
        Book book=bookService.findBookById(id);
        Gson gson = new Gson();
        String book_json = gson.toJson(book);
        System.out.println(book_json);
        return book_json;
    }

    @RequestMapping(value="/manage/admin/book/add",method = RequestMethod.POST)
    public Msg addBook(@RequestBody BookJson bookJson){
        if(SessionUtil.getUserType()!= Constant.ADMIN)
        {
            return MsgUtil.makeMsg(MsgCode.ERROR,MsgUtil.ADMIN_NO_AUTH);
        }

        Book book=new Book(bookJson);
        boolean op=bookService.addBook(book);
        if(op)
            return MsgUtil.makeMsg(MsgCode.SUCCESS,"ADD BOOK SUCCESS!");
        return MsgUtil.makeMsg(MsgCode.ERROR,"ADD BOOK FAIL!");
    }

    @RequestMapping(value="/manage/admin/book/modify",method = RequestMethod.POST)
    public Msg modifyBook(@RequestBody BookJson bookJson){
        if(SessionUtil.getUserType()!= Constant.ADMIN)
        {
            return MsgUtil.makeMsg(MsgCode.ERROR,MsgUtil.ADMIN_NO_AUTH);
        }

        Book book=new Book(bookJson);
        boolean op=bookService.modifyBook(book);
        if(op)
            return MsgUtil.makeMsg(MsgCode.SUCCESS,"MODIFY BOOK SUCCESS!");
        return MsgUtil.makeMsg(MsgCode.ERROR,"MODIFY BOOK FAIL!");
    }

    @RequestMapping(value="/manage/admin/book/del",method = RequestMethod.POST)
    public Msg delBook(@RequestBody Map<String,Integer> params){
        if(SessionUtil.getUserType()!= Constant.ADMIN)
        {
            return MsgUtil.makeMsg(MsgCode.ERROR,MsgUtil.ADMIN_NO_AUTH);
        }

        Integer book_id=params.get(Constant.BOOK_ID);
        System.out.println("del book id:"+book_id);
        boolean op=bookService.delBook(book_id);
        if(op)
            return MsgUtil.makeMsg(MsgCode.SUCCESS,"DEL BOOK SUCCESS!");
        return MsgUtil.makeMsg(MsgCode.ERROR,"DEL BOOK FAIL!");
    }

}
