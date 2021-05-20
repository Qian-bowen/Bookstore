package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findOne(Integer id){
        return bookRepository.getOne(id);
    }

    @Override
    public List<Book> getBooks(Integer fetch_num,Integer fetch_begin) {
        return bookRepository.getBooks();
    }

}
