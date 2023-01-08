package com.example.team8.websiteMonitor.service;

import com.example.team8.websiteMonitor.Jobs.HelloWorldJob;
import com.example.team8.websiteMonitor.info.TimerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final SchedulerService scheduler;

    @Autowired
    public JobService(final SchedulerService scheduler) {
        this.scheduler = scheduler;
    }

    public void runHelloWorld(String frequency) {
        int time = 0;
        int runTime = 0;
        char timeDataChar = frequency.charAt(0);
        String timeDataString = "" + timeDataChar;
        if(frequency.charAt(1) == 'm'){
            runTime = Integer.parseInt(timeDataString);
            time = runTime * 60 * 1000;
        }
        else if(frequency.charAt(1) == 'h'){
            runTime = Integer.parseInt(timeDataString);
            time = runTime * 60 * 1000 * 60;
        }
        final TimerInfo info = new TimerInfo();
        info.setTotalFireCount(1000);
        info.setRepeatIntervalMs(time);
        info.setInitialOffsetMs(1000);
        info.setCallbackData("test");
        scheduler.schedule(HelloWorldJob.class, info);
    }


}
