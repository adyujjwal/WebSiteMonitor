package com.example.team8.websiteMonitor.Jobs;

import com.example.team8.websiteMonitor.service.WebSiteMonitorService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

@Component
public class HelloWorldJob implements Job {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldJob.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String userId = WebSiteMonitorService.getUserIdData().toString();
        String url = WebSiteMonitorService.getUrlData();
        String webisteStatus = "";
        int timeout = 2000;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url)
                    .openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                webisteStatus = "UP";
            } else {
                webisteStatus = "DOWN";
            }
        } catch (IOException exception) {
            webisteStatus = "DOWN";
        }
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String currentTime = date.toString();
        jdbcTemplate.update("insert into checkResponse(checkId, status, hitTime)" + "values(?,?,?)",
                new Object[]{
                        userId, webisteStatus, currentTime
                });
        LOG.info("Check Executed Successfully!");
    }
}
