package com.sisyphe.bookstore.controller;

import com.google.gson.Gson;
import com.sisyphe.bookstore.domain.BookBuyStat;
import com.sisyphe.bookstore.domain.BookSellStat;
import com.sisyphe.bookstore.domain.StatisticSet;
import com.sisyphe.bookstore.domain.UserConsumeStat;
import com.sisyphe.bookstore.service.BookService;
import com.sisyphe.bookstore.service.OrderService;
import com.sisyphe.bookstore.service.StatisticService;
import com.sisyphe.bookstore.service.UserService;
import com.sisyphe.bookstore.utils.sessionutils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatisticController {
    private StatisticService statisticService;
    private UserService userService;

    @Autowired
    public StatisticController(StatisticService statisticService,UserService userService)
    {
        this.statisticService=statisticService;
        this.userService=userService;
    }

    @RequestMapping(value = "/statistic/books")
    public String statisticBooks(@RequestBody StatisticSet statisticSet)
    {
        List<BookSellStat> bookSellStatList = statisticService.statBookSell(statisticSet);
        Gson gson=new Gson();
        return gson.toJson(bookSellStatList);
    }

    @RequestMapping(value = "/statistic/admin/user-consume")
    public String statisticUserConsume(@RequestBody StatisticSet statisticSet)
    {
        List<UserConsumeStat> userConsumeStatList = statisticService.statUserConsume(statisticSet);
        Gson gson=new Gson();
        return gson.toJson(userConsumeStatList);
    }

    @RequestMapping(value = "/statistic/personal/purchase")
    public String statisticPersonalOrder(@RequestBody StatisticSet statisticSet)
    {
        Integer userId= SessionUtil.getUserId();
        System.out.println("stat order of user:"+userId);
        List<BookBuyStat> bookBuyStatList = statisticService.statPersonalMostConsume(statisticSet,userId);
        Gson gson=new Gson();
        return gson.toJson(bookBuyStatList);
    }

}
