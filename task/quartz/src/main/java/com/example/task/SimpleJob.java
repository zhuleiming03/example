package com.example.task;

import org.quartz.*;

import java.util.Date;

public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println(">>a simple job begin");

        JobDataMap dataMap = context.getMergedJobDataMap();
        String name = dataMap.getString("name");  //获取名字
        Integer age = dataMap.getInt("age");    //获取年龄

        System.out.println("name: " + name + "  age:" + age);

        JobDetail jobDetail = context.getJobDetail();
        String jobName = jobDetail.getKey().getName();
        String jobGroup = jobDetail.getKey().getGroup();

        System.out.println("jobName: " + jobName + "  jobGroup:" + jobGroup);

        Trigger trigger = context.getTrigger();
        String triggerName = trigger.getKey().getName();
        String triggerGroup = trigger.getKey().getGroup();
        Date startTime = trigger.getStartTime();  //获取任务开始时间
        Date endTime = trigger.getEndTime();    //获取任务结束时间

        System.out.println("triggerName: " + triggerName + "  triggerGroup:" + triggerGroup);
    }
}
