package com.sisyphe.bookstore.dao;

import com.sisyphe.bookstore.entity.BookRemark;

import java.util.List;

public interface BookRemarkDao {
    List<BookRemark> findBookRemarkByBookId(Integer bookId);
    void addBookRemarkByBookId(BookRemark br);
}
