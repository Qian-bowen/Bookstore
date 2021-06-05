package com.sisyphe.bookstore.controller;

import com.google.gson.Gson;
import com.sisyphe.bookstore.domain.BookSellStat;
import com.sisyphe.bookstore.domain.StatisticSet;
import com.sisyphe.bookstore.service.BookService;
import com.sisyphe.bookstore.service.OrderService;
import com.sisyphe.bookstore.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {
    private StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService)
    {
        this.statisticService=statisticService;
    }

    @RequestMapping(value = "/statistic/books")
    public String statisticBooks(@RequestBody StatisticSet statisticSet)
    {
        List<BookSellStat> bookSellStatList = statisticService.statBookSell(statisticSet);
        Gson gson=new Gson();
        return gson.toJson(bookSellStatList);
    }
}
