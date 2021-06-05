package com.sisyphe.bookstore.domain;

import com.sisyphe.bookstore.constant.StatisticType;

public class StatisticSet {
    StatisticType type;
    int statNum;
    String lower_time;
    String upper_time;

    public StatisticSet(StatisticType statisticType, int statNum, String lower_time, String upper_time)
    {
        this.type=statisticType;
        this.statNum=statNum;
        this.lower_time=lower_time;
        this.upper_time=upper_time;
    }

    public StatisticType getType(){return type;}
    public int getStatNum() {
        return statNum;
    }

    public String getLower_time() {
        return lower_time;
    }

    public String getUpper_time() {
        return upper_time;
    }
}
