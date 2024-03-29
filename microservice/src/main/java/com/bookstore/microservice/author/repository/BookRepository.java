package com.bookstore.microservice.author.repository;

import com.bookstore.microservice.author.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select b from Book b where b.name=:name")
    List<Book> getBooksByName(@Param("name") String name);
}