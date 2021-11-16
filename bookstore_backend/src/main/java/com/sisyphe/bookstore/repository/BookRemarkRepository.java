package com.sisyphe.bookstore.repository;

import com.sisyphe.bookstore.entity.BookRemark;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "bookstore", path = "bookstore")
public interface BookRemarkRepository extends MongoRepository<BookRemark,String> {
    List<BookRemark> findBookRemarkByBookId(@Param("bookId") Integer bookId);
}
