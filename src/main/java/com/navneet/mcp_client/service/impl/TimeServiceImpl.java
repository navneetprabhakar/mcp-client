package com.navneet.mcp_client.service.impl;

import com.navneet.mcp_client.service.TimeService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimeServiceImpl implements TimeService {

    private static final String DATE_FORMAT="yyyy-MM-dd";
    private static final String DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";

    @Tool(name = "current_time_millis", description = "Returns the current time in milliseconds since the Unix epoch (January 1, 1970, 00:00:00 UTC)")
    @Override
    public Long currentTimeInMillis() {
        return System.currentTimeMillis();
    }

    @Tool(name = "current_time_seconds", description = "Returns the current time in seconds since the Unix epoch (January 1, 1970, 00:00:00 UTC)")
    @Override
    public Long currentTimeInSeconds() {
        return System.currentTimeMillis()/1000;
    }

    @Tool(name = "current_date", description = "Returns the current date in yyyy-MM-dd format")
    @Override
    public String currentDate() {
        return new SimpleDateFormat(DATE_FORMAT).format(new Date());
    }

    @Tool(name = "current_date_and_time", description = "Returns the current date and time in yyyy-MM-dd HH:mm:ss format")
    @Override
    public String currentDateAndTime() {
        return new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date());
    }

    @Tool(name = "date_to_millis", description = "Converts a date string in yyyy-MM-dd HH:mm:ss format to milliseconds since Unix epoch")
    @Override
    public Long dateToMillis(String dateStr) {
        try{
            return new SimpleDateFormat(DATE_TIME_FORMAT).parse(dateStr).getTime();
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Tool(name = "date_to_seconds", description = "Converts a date string in yyyy-MM-dd HH:mm:ss format to seconds since Unix epoch")
    @Override
    public Long dateToSeconds(String dateStr) {
        return dateToMillis(dateStr)/1000;
    }

    @Tool(name = "millis_to_date", description = "Converts milliseconds since Unix epoch to a date string in yyyy-MM-dd HH:mm:ss format")
    @Override
    public String millisToDate(Long millis) {
        return new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date(millis));
    }

    @Tool(name = "seconds_to_date", description = "Converts seconds since Unix epoch to a date string in yyyy-MM-dd HH:mm:ss format")
    @Override
    public String secondsToDate(Long seconds) {
        return new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date(seconds*1000));
    }
}
