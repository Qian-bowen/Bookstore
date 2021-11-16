package com.sisyphe.bookstore.daoimpl;

import com.sisyphe.bookstore.dao.BookRemarkDao;
import com.sisyphe.bookstore.entity.BookRemark;
import com.sisyphe.bookstore.repository.BookRemarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRemarkDaoImpl implements BookRemarkDao {
    @Autowired
    private BookRemarkRepository bookRemarkRepository;

    @Override
    public List<BookRemark> findBookRemarkByBookId(Integer bookId)
    {
        return bookRemarkRepository.findBookRemarkByBookId(bookId);
    }

    @Override
    public void addBookRemarkByBookId(BookRemark br)
    {
        bookRemarkRepository.save(br);
    }
}
