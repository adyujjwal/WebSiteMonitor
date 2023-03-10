package com.example.team8.websiteMonitor.service;

import com.example.team8.websiteMonitor.Jobs.HelloWorldJob;
import com.example.team8.websiteMonitor.info.TimerInfo;
import com.example.team8.websiteMonitor.util.TimerUtil;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class SchedulerService {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldJob.class);
    private final Scheduler scheduler;

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void schedule(final Class jobClass, final TimerInfo timerInfo) {
        final JobDetail jobDetail = TimerUtil.buildJobDetail(jobClass, timerInfo);
        final Trigger trigger = TimerUtil.buildTrigger(jobClass, timerInfo);
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @PostConstruct
    public void init() {
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}