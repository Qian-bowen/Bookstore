package com.sisyphe.bookstore.serviceimpl;

import com.sisyphe.bookstore.Json.UserJson;
import com.sisyphe.bookstore.constant.StatisticType;
import com.sisyphe.bookstore.dao.BookDao;
import com.sisyphe.bookstore.dao.OrderDao;
import com.sisyphe.bookstore.dao.UserDao;
import com.sisyphe.bookstore.domain.BookBuyStat;
import com.sisyphe.bookstore.domain.BookSellStat;
import com.sisyphe.bookstore.domain.StatisticSet;
import com.sisyphe.bookstore.domain.UserConsumeStat;
import com.sisyphe.bookstore.entity.Book;
import com.sisyphe.bookstore.entity.User;
import com.sisyphe.bookstore.entity.entityComp.BookBuyComp;
import com.sisyphe.bookstore.entity.entityComp.BookComp;
import com.sisyphe.bookstore.entity.entityComp.UserConsumeComp;
import com.sisyphe.bookstore.service.StatisticService;
import com.sisyphe.bookstore.utils.convert.TimeConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    private OrderDao orderDao;
    private BookDao bookDao;
    private UserDao userDao;

    @Autowired
    public StatisticServiceImpl(OrderDao orderDao,BookDao bookDao,UserDao userDao)
    {
        this.orderDao=orderDao;
        this.bookDao=bookDao;
        this.userDao=userDao;
    }

    @Override
    public List<BookSellStat> statBookSell(StatisticSet statisticSet)
    {
        List<BookSellStat> bookSellStats=new ArrayList<>();
        if(statisticSet.getType()== StatisticType.STAT_UP)
        {
            try{
                TimeConvert timeConvert=new TimeConvert("yyyy-MM-dd hh:mm:ss","UTC");
                Timestamp lower_timestamp=timeConvert.StringToTimestamp(statisticSet.getLower_time());
                Timestamp upper_timestamp=timeConvert.StringToTimestamp(statisticSet.getUpper_time());
                List<BookComp> bookCompList =orderDao.bestSellBook(statisticSet.getStatNum(),lower_timestamp,upper_timestamp);
                for(BookComp bookComp : bookCompList)
                {
                    Book book=bookDao.findOne(bookComp.getBookId());
                    bookSellStats.add(new BookSellStat(book, bookComp.getSell_num()));
                }
            }catch(Exception e){}
        }
        return bookSellStats;
    }

    @Override
    public List<UserConsumeStat> statUserConsume(StatisticSet statisticSet)
    {
        List<UserConsumeStat> userConsumeStatList=new ArrayList<>();
        if(statisticSet.getType()== StatisticType.STAT_UP)
        {
            try{
                TimeConvert timeConvert=new TimeConvert("yyyy-MM-dd hh:mm:ss","UTC");
                Timestamp lower_timestamp=timeConvert.StringToTimestamp(statisticSet.getLower_time());
                Timestamp upper_timestamp=timeConvert.StringToTimestamp(statisticSet.getUpper_time());
                List<UserConsumeComp> userConsumeCompList=orderDao.userMostConsume(statisticSet.getStatNum(),lower_timestamp,upper_timestamp);
                for(UserConsumeComp userConsumeComp:userConsumeCompList)
                {
                    User user=userDao.findUserById(userConsumeComp.getUserId());
                    UserJson userJson=new UserJson(user,-1);
                    BigDecimal total_consume=userConsumeComp.getTotal_consume();
                    userConsumeStatList.add(new UserConsumeStat(userJson,total_consume));
                }
            }catch(Exception e){}
        }
        return userConsumeStatList;
    }

    @Override
    public List<BookBuyStat> statPersonalMostConsume(StatisticSet statisticSet,int user_id)
    {
        List<BookBuyStat> bookBuyStats=new ArrayList<>();
        try{
            TimeConvert timeConvert=new TimeConvert("yyyy-MM-dd hh:mm:ss","UTC");
            Timestamp lower_timestamp=timeConvert.StringToTimestamp(statisticSet.getLower_time());
            Timestamp upper_timestamp=timeConvert.StringToTimestamp(statisticSet.getUpper_time());
            List<BookBuyComp> bookBuyCompList=orderDao.PersonalMostConsume(statisticSet.getStatNum(),user_id,lower_timestamp,upper_timestamp);
            for(BookBuyComp bookBuyComp : bookBuyCompList)
            {
                Book book=bookDao.findOne(bookBuyComp.getBookId());
                Long total_num=bookBuyComp.getTotal_num();
                BigDecimal total_price=bookBuyComp.getItem_price();
                bookBuyStats.add(new BookBuyStat(book,total_num,total_price));
            }
        }catch(Exception e){}
        return bookBuyStats;
    }

}
