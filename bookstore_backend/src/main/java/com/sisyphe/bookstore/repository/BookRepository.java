package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from Book b")
    List<Book> getBooks(Pageable pageable);

    @Query(value = "from Book where bookId = :id")
    Book getOne(@Param("id") Integer id);

    @Query("select b from Book b where b.name like concat('%',:name,'%')")
    List<Book> getBooksByName(@Param("name") String name);

    @Query("select b from Book b where b.name=:name")
    List<Book> getBooksByExactName(@Param("name") String name);

}
