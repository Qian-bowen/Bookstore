package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.dao.BookDao;
import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName BookServiceImpl
 * @Description the Implement of BookService
 * @Author thunderBoy
 * @Date 2019/11/6 16:04
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks(Integer fetch_num,Integer fetch_begin) {
        return bookDao.getBooks(fetch_num,fetch_begin);
    }
}