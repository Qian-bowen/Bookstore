package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    private BookRepository bookRepository;

    @Autowired
    public BookDaoImpl(BookRepository bookRepository)
    {
        this.bookRepository=bookRepository;
    }

    @Override
    public Book findOne(Integer id){
        return bookRepository.getOne(id);
    }

    @Override
    public List<Book> getBooks(Integer fetch_num,Integer fetch_begin) {
        System.out.println("fetch begin:"+fetch_begin);
        Integer pageIdx=fetch_begin/fetch_num;
        Pageable pageRequest= PageRequest.of(pageIdx, fetch_num);
        return bookRepository.getBooks(pageRequest);
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
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public Book modifyBook(Book book)
    {

        return bookRepository.saveAndFlush(book);
    }

    @Override
    public void delBook(Integer id)
    {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean reduceInventory(Integer id,Integer reduceNum)
    {
        Book book=bookRepository.getOne(id);
        if(!book.reduceInventory(reduceNum))
            return false;
        bookRepository.save(book);
        return true;
    }

}
