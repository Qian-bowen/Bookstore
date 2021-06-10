package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.constant.SearchType;
import com.sisyphe.bookstore.dao.BookDao;
import com.sisyphe.bookstore.domain.BookSearch;
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

    private BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao)
    {
        this.bookDao=bookDao;
    }

    @Override
    public Book findBookById(Integer id){
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks(Integer fetch_num,Integer fetch_begin) {
        return bookDao.getBooks(fetch_num,fetch_begin);

    }

    @Override
    public List<Book> searchBooks(BookSearch bookSearch){
        if(bookSearch.getType()== SearchType.BY_NAME)
            return bookDao.getBooksByName(bookSearch.getName());
        return null;
    }

    @Override
    public boolean addBook(Book book){
        Book addBook=bookDao.addBook(book);
        if(addBook==null)
            return false;
        return true;
    }

    @Override
    public boolean modifyBook(Book book){
        Book modifyBook=bookDao.modifyBook(book);
        if(modifyBook==null)
            return false;
        return true;
    }

    @Override
    public boolean delBook(Integer id){
        Book book=bookDao.findOne(id);
        if(book==null)
            return false;
        bookDao.delBook(id);
        return true;
    }

    @Override
    public boolean reduceInventory(Integer id,Integer reduceNum)
    {
        Book book=bookDao.findOne(id);
        if(book.getInventory()<reduceNum||reduceNum<0)
            return false;
        bookDao.addBook(book);
        return true;
    }
}