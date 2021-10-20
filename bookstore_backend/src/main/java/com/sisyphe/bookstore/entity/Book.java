package com.sisyphe.bookstore.entity;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sisyphe.bookstore.Json.BookJson;
import com.sisyphe.bookstore.utils.RedisUtil;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "bookId")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "inventory")
    private Integer inventory;

    @Lob
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    public Book() {
    }

    public Book(int p_bid, String p_isbn, String p_name, String p_type, String p_author, BigDecimal p_price, String p_dsp, Integer p_ivt, byte[] p_img) {
        bookId = p_bid;
        isbn = p_isbn;
        name = p_name;
        type = p_type;
        author = p_author;
        price = p_price;
        description = p_dsp;
        inventory = p_ivt;
        image = p_img;
    }

    public Book(String p_isbn, String p_name, String p_type, String p_author, BigDecimal p_price, String p_dsp, Integer p_ivt, byte[] p_img) {
        isbn = p_isbn;
        name = p_name;
        type = p_type;
        author = p_author;
        price = p_price;
        description = p_dsp;
        inventory = p_ivt;
        image = p_img;
    }

    public Book(BookJson bookJson) {
        System.out.println(bookJson.image);
        bookId = bookJson.bookId;
        isbn = bookJson.isbn;
        name = bookJson.name;
        type = bookJson.type;
        author = bookJson.author;
        price = bookJson.price;
        description = bookJson.description;
        inventory = bookJson.inventory;
        image = Base64.decodeBase64(bookJson.image);
    }

    public String get_name() {
        return name;
    }

    public int getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getType() {
        return type;
    }

    public String getAuthor() {
        return author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getInventory() {
        return inventory;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean reduceInventory(Integer reduceNum) {
        if (reduceNum > inventory)
            return false;
        inventory -= reduceNum;
        return true;
    }

    public JSONObject getBookJson() {
        JSONObject obj = new JSONObject();
        obj.put("bookId", this.bookId);
        obj.put("isbn", this.isbn);
        obj.put("name", this.name);
        obj.put("type", this.type);
        obj.put("author", this.author);
        obj.put("price", this.price);
        obj.put("description", this.description);
        obj.put("inventory", this.inventory);
        obj.put("image", new String(Base64.encodeBase64(this.image)));
        return obj;
    }

    public static JSONArray book2json(List<Book> bookList) {
        JSONArray jsonArray = new JSONArray();
        for (Book book : bookList) {
            JSONObject obj = new JSONObject();
            obj.put("bookId", book.bookId);
            obj.put("isbn", book.isbn);
            obj.put("name", book.name);
            obj.put("type", book.type);
            obj.put("author", book.author);
            obj.put("price", book.price);
            obj.put("description", book.description);
            obj.put("inventory", book.inventory);
            obj.put("image", new String(Base64.encodeBase64(book.image)));
            jsonArray.add(obj);
        }
        return jsonArray;
    }

    public static List<Book> bookJson2book(List<BookJson> bookList) {
        List<Book> ret = new ArrayList<>();
        for (BookJson bookJson : bookList) {
            Book book = new Book(bookJson);
            ret.add(book);
        }
        return ret;
    }

}
