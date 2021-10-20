package com.sisyphe.bookstore.service;

import com.sisyphe.bookstore.domain.BookBuyStat;
import com.sisyphe.bookstore.domain.BookSellStat;
import com.sisyphe.bookstore.domain.StatisticSet;
import com.sisyphe.bookstore.domain.UserConsumeStat;

import java.util.List;

public interface StatisticService {
    List<BookSellStat> statBookSell(StatisticSet statisticSet);

    List<UserConsumeStat> statUserConsume(StatisticSet statisticSet);

    List<BookBuyStat> statPersonalMostConsume(StatisticSet statisticSet, int user_id);
}
