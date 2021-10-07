package com.sisyphe.bookstore.daoimpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sisyphe.bookstore.Json.BookJson;
import com.sisyphe.bookstore.repository.BookRepository;
import com.sisyphe.bookstore.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.dao.BookDao;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private BookRepository bookRepository;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    public BookDaoImpl(BookRepository bookRepository)
    {
        this.bookRepository=bookRepository;
    }

    @Override
    public Book findOne(Integer id){
        Book book=null;
        String key="bookid"+id;
        Object bookGet=redisUtil.get(key);
        if(bookGet==null)
        {
            System.out.println("from database");
            book=bookRepository.getOne(id);
            redisUtil.set(key,JSONObject.toJSONString(book.getBookJson()));
        }
        else
        {
            System.out.println("from redis");
            BookJson bookJson= JSONObject.parseObject(bookGet.toString(), BookJson.class);
            book=new Book(bookJson);
        }
        return book;
    }

    @Override
    public List<Book> getBooks(Integer fetch_num,Integer fetch_begin) {
        System.out.println("fetch begin:"+fetch_begin);
        Integer pageIdx=fetch_begin/fetch_num;
        Pageable pageRequest= PageRequest.of(pageIdx, fetch_num);

//        List<Book> bookList=null;
//        String key="book"+pageIdx+","+fetch_num;
//        Object list=redisUtil.get(key);
//        if(list==null)
//        {
//            System.out.println("from database");
//            bookList=bookRepository.getBooks(pageRequest);
//            JSONArray jsonArray=Book.book2json(bookList);
//            redisUtil.set(key,jsonArray.toString());
//        }
//        else
//        {
//            System.out.println("from redis");
//            List<BookJson> bookJsonList= JSONArray.parseArray(list.toString(), BookJson.class);
//            bookList=Book.bookJson2book(bookJsonList);
//        }
        List<Book> bookList=bookRepository.getBooks(pageRequest);

        return bookList;
    }

    @Override
    public List<Book> getBooksByName(String name){return bookRepository.getBooksByName(name);}

    @Override
    public List<Book> getBooksByExactName(String name){return bookRepository.getBooksByExactName(name);}

    @Override
    public Book addBook(Book book)
    {
        List<Book> books = bookRepository.getBooksByExactName(book.get_name());
        if(!books.isEmpty()) return null;

        bookRepository.saveAndFlush(book);

        //add book to redis
        String key="bookid"+book.getBookId();
        Object bookGet=redisUtil.get(key);
        if(bookGet!=null)
        {
            redisUtil.del(key);
        }
        redisUtil.set(key,JSONObject.toJSONString(book.getBookJson()));
        return book;
    }

    @Override
    public Book modifyBook(Book book)
    {
        bookRepository.saveAndFlush(book);

        //modify book to redis
        String key="bookid"+book.getBookId();
        Object bookGet=redisUtil.get(key);
        if(bookGet!=null)
        {
            redisUtil.del(key);
        }
        redisUtil.set(key,JSONObject.toJSONString(book.getBookJson()));

        return book;
    }

    @Override
    public void delBook(Integer id)
    {
        bookRepository.deleteById(id);

        //add book to redis
        String key="bookid"+id;
        Object bookGet=redisUtil.get(key);
        if(bookGet!=null)
        {
            redisUtil.del(key);
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean reduceInventory(Integer id,Integer reduceNum)
    {
        Book book=bookRepository.getOne(id);
        if(!book.reduceInventory(reduceNum))
            return false;
        bookRepository.save(book);
        String key="bookid"+id;
        redisUtil.decr(key,reduceNum);
        return true;
    }

}
