package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from Book b")
    List<Book> getBooks();

    @Query(value = "from Book where bookId = :id")
    Book getOne(@Param("id") Integer id);
}
