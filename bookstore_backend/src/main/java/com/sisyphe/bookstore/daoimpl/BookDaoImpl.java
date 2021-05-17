package com.sisyphe.bookstore.daoimpl;

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
    JdbcTemplate jdbcTemplate;

    @Override
    public Book findOne(Integer id){
        List<Book> result;
        String sql="SELECT * FROM book WHERE id=?";
        result=jdbcTemplate.query(sql,
                (rs,rowNum)->new Book(
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("inventory"),
                        rs.getString("image")),
                new Object[]{id});
        return result.get(0);
    }

    @Override
    public List<Book> getBooks(Integer fetch_num,Integer fetch_begin) {
        System.out.println("fetch num in sql:"+fetch_num);
        List<Book> result;
        String sql="SELECT * FROM book LIMIT "+Integer.toString(fetch_begin)+','+Integer.toString(fetch_num);
        result=jdbcTemplate.query(sql,
                (rs,rowNum)->new Book(
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("author"),
                        rs.getDouble("price"),
                        rs.getString("description"),
                        rs.getInt("inventory"),
                        rs.getString("image")
                )
        );
        return result;
    }

}
