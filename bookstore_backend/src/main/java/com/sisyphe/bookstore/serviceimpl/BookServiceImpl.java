package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.constant.SearchType;
import com.sisyphe.bookstore.dao.BookDao;
import com.sisyphe.bookstore.dao.BookRemarkDao;
import com.sisyphe.bookstore.domain.BookSearch;
import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.entity.BookRemark;
import com.sisyphe.bookstore.entity.Tag;
import com.sisyphe.bookstore.repository.BookRepository;
import com.sisyphe.bookstore.repository.TagRepository;
import com.sisyphe.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BookServiceImpl
 * @Description the Implement of BookService
 * @Author thunderBoy
 * @Date 2019/11/6 16:04
 */

@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    @Autowired
    private BookRemarkDao bookRemarkDao;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book findBookById(Integer id) {
        return bookDao.findOne(id);
    }

    @Override
    public List<Book> getBooks(Integer fetch_num, Integer fetch_begin) {
        return bookDao.getBooks(fetch_num, fetch_begin);

    }

    @Override
    public List<Book> searchBooks(BookSearch bookSearch) {
        if (bookSearch.getType() == SearchType.BY_NAME)
            return bookDao.getBooksByName(bookSearch.getName());
        return null;
    }

    @Override
    public boolean addBook(Book book) {
        Book addBook = bookDao.addBook(book);
        if (addBook == null)
            return false;
        return true;
    }

    @Override
    public boolean modifyBook(Book book) {
        Book modifyBook = bookDao.modifyBook(book);
        if (modifyBook == null)
            return false;
        return true;
    }

    @Override
    public boolean delBook(Integer id) {
        Book book = bookDao.findOne(id);
        if (book == null)
            return false;
        bookDao.delBook(id);
        return true;
    }

    @Override
    public boolean reduceInventory(Integer id, Integer reduceNum) {
        Book book = bookDao.findOne(id);
        if (book.getInventory() < reduceNum || reduceNum < 0)
            return false;
        bookDao.addBook(book);
        return true;
    }

    @Override
    public List<BookRemark> findBookRemarkByBookId(Integer bookId)
    {
        return bookRemarkDao.findBookRemarkByBookId(bookId);
    }

    @Override
    public void addBookRemarkByBookId(BookRemark br)
    {
        bookRemarkDao.addBookRemarkByBookId(br);
    }

    @Override
    public List<Book> getRelatedBook(String tag)
    {
        List<Book> bookList=new ArrayList<>();
        List<Tag> tagList=tagRepository.getRelateTag(tag);
        if(tagList.isEmpty()) return bookList;
        HashMap<Integer, Book> bookMap = new HashMap<>();
        for(Tag t:tagList)
        {
            List<Book> books=bookRepository.getBooksByTag(t.getTag());
            if(books.isEmpty()) continue;
            for(Book b:books)
            {
                if(bookMap.containsKey(b.getBookId())) continue;
                bookMap.put(b.getBookId(),b);
            }
        }
        for (Map.Entry elem : bookMap.entrySet())
        {
            bookList.add((Book) elem.getValue());
        }
        return bookList;
    }

}