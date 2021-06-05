package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.constant.StatisticType;
import com.sisyphe.bookstore.dao.BookDao;
import com.sisyphe.bookstore.dao.OrderDao;
import com.sisyphe.bookstore.domain.BookBuyStat;
import com.sisyphe.bookstore.domain.BookSellStat;
import com.sisyphe.bookstore.domain.StatisticSet;
import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.entity.entityComp.BookSellComp;
import com.sisyphe.bookstore.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    private OrderDao orderDao;
    private BookDao bookDao;

    @Autowired
    public StatisticServiceImpl(OrderDao orderDao,BookDao bookDao)
    {
        this.orderDao=orderDao;
        this.bookDao=bookDao;
    }

    @Override
    public List<BookSellStat> statBookSell(StatisticSet statisticSet)
    {
        List<BookSellStat> bookSellStats=new ArrayList<>();
        if(statisticSet.getType()== StatisticType.STAT_UP)
        {
            List<BookSellComp> bookSellCompList=orderDao.bestSellBook(statisticSet.getStatNum());
            for(BookSellComp bookSellComp:bookSellCompList)
            {
                Book book=bookDao.findOne(bookSellComp.getBookId());
                bookSellStats.add(new BookSellStat(book,bookSellComp.getSell_num()));
            }
        }
        return bookSellStats;
    }

}
