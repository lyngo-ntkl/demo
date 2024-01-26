package com.lyngo.demojavascheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class ScheduleComponent {
    @Autowired
    private Environment env;

    @Scheduled(cron = "${date}")
    public void schedule(){
        System.out.println("Current time: " + new Date());
        System.out.println(env.getProperty("date"));
//        CronExpression cronExpression = CronExpression.parse("${date}");
//        LocalDateTime date = cronExpression.next(LocalDateTime.now());
//        System.out.println(date);
    }
}
