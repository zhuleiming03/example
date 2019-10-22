package com.example.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskApplication.class, args);

        JobDetail jobDetail = JobBuilder
                .newJob(SimpleJob.class)
                .withIdentity("jobName", "jobGroup")  //定义job的名字和组
                .usingJobData("name", "yanjun")  //传参数，key:name value:yanjun
                .usingJobData("age", 18)  //传参数，key:age value:18
                .build();

        //定义开始时间
        Date startDate = new Date();

        //定义结束时间，在开始时间之后10秒
        Date endDate = new Date(startDate.getTime() + 10 * 1000);
        //触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerName", "triggerGroup")
                .startNow()  //现在开始执行
                .startAt(startDate)  //在指定的时间开始执行
                .endAt(endDate)      //在指定的时间结束执行
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .repeatForever()  //永远执行下去
                        .withRepeatCount(10)  //执行10次
                        .withIntervalInSeconds(1)  //一秒钟执行一次
                )
                .build();


        try {
            SchedulerFactory factory = new StdSchedulerFactory();
            Scheduler scheduler = factory.getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start(); //开始执行
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
