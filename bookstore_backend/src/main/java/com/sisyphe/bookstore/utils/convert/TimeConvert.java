package com.sisyphe.bookstore.utils.convert;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeConvert {
    private SimpleDateFormat dateFormat;

    public TimeConvert(String dataFormat, String timeZone) {
        dateFormat = new SimpleDateFormat(dataFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
    }

    public Timestamp StringToTimestamp(String timeString) throws ParseException {
        Date date = dateFormat.parse(timeString);
        Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        return timestamp;
    }
}
