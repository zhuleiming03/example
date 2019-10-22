package com.example.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleTask {

    @Scheduled(cron = "${jobs.scheduled.simpleTask}")
    public void simpleTask(){
        System.out.println("This is a Plan Task Test!");
    }
}
