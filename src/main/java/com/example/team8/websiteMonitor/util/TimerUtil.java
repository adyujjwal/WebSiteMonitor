package com.example.team8.websiteMonitor.util;

import com.example.team8.websiteMonitor.info.TimerInfo;
import org.quartz.*;

import java.util.Date;

public final class TimerUtil {
    private TimerUtil(){ }

    public static JobDetail buildJobDetail(final Class jobClass,final TimerInfo info){
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobClass.getSimpleName(), info);

        return JobBuilder.
                newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .setJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class jobClass, final TimerInfo timerInfo){
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(timerInfo.getRepeatIntervalMs());
        if(timerInfo.isRunForever()){
            builder = builder.repeatForever();
        }else{
            builder = builder.withRepeatCount(timerInfo.getTotalFireCount()-1);
        }
        return TriggerBuilder
                .newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .withSchedule(builder)
                .startAt(new Date(System.currentTimeMillis()+timerInfo.getInitialOffsetMs()))
                .build();
    }

}

