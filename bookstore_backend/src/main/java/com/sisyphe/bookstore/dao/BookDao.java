package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.entity.Book;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookDao {
    Book findOne(Integer id);

    List<Book> getBooks(Integer fetch_num,Integer fetch_begin);
    List<Book> getBooksByName(String name);
    List<Book> getBooksByExactName(String name);

    Book addBook(Book book);
    Book modifyBook(Book book);
    void delBook(Integer id);

    boolean reduceInventory(Integer id,Integer reduceNum);
}