package com.sisyphe.bookstore.service;

import com.sisyphe.bookstore.domain.BookBuyStat;
import com.sisyphe.bookstore.domain.BookSellStat;
import com.sisyphe.bookstore.domain.StatisticSet;

import java.util.List;

public interface StatisticService {
    List<BookSellStat> statBookSell(StatisticSet statisticSet);
    //void statUserConsume()
}
