package com.example.team8.websiteMonitor.service;

import com.example.team8.websiteMonitor.model.*;
import com.example.team8.websiteMonitor.repository.WebsiteMonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebSiteMonitorService {

    @Autowired
    WebsiteMonitorRepository websiteMonitorRepository;

    private final JobService jobService;
    private static String frequencyData = "";
    private static String urlData = "";
    private static Integer userIdData;

    public static String getFrequencyData() {
        return frequencyData;
    }

    public static String getUrlData() {
        return urlData;
    }

    public static Integer getUserIdData() {
        return userIdData;
    }

    @Autowired
    public WebSiteMonitorService(JobService jobService) {
        this.jobService = jobService;
    }

    public WebMonitorResponse getWebsiteCurrentStatus(String url) {
        WebMonitorResponse webMonitorResponse = new WebMonitorResponse();
        int timeout = 2000;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url)
                    .openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                webMonitorResponse.setMessage("The Website is Down :(");
                return webMonitorResponse;
            }
        } catch (IOException exception) {
            webMonitorResponse.setMessage("The Website is Down :(");
            return webMonitorResponse;
        }
        webMonitorResponse.setMessage("The Website is Up and running! :)");
        return webMonitorResponse;
    }

    public WebMonitorResponse createNewCheck(WebSiteMonitorInput webSiteMonitorInput) {
        String str = webSiteMonitorInput.getFrequency();
        str = str.replaceAll("\\s", "");
        String frequency="";
        if(str.charAt(1) == 'm' || str.charAt(1) == 'M'){
            frequency = str.charAt(0)+"m";
        }
        else if(str.charAt(1) == 'h' || str.charAt(1) == 'H'){
            frequency = str.charAt(0)+"h";
        }
        webSiteMonitorInput.setFrequency(frequency);
        WebMonitorResponse webMonitorResponse = new WebMonitorResponse();
        int result = websiteMonitorRepository.createNewCheck(webSiteMonitorInput);
        if (result == 1) {
            webMonitorResponse.setMessage("Check Created Successfully!");
        } else {
            webMonitorResponse.setMessage("Something Went Wrong!");
        }
        return webMonitorResponse;
    }

    public WebMonitorResponse createNewUser(WebMonitorUserInput webMonitorUserInput) {
        WebMonitorResponse webMonitorResponse = new WebMonitorResponse();
        int result = websiteMonitorRepository.createNewUser(webMonitorUserInput);
        if (result == 1) {
            webMonitorResponse.setMessage("User Created Successfully!");
        } else {
            webMonitorResponse.setMessage("Something Went Wrong!");
        }
        return webMonitorResponse;
    }

    public WebMonitorResponse deleteCheck(int id) {
        WebMonitorResponse webMonitorResponse = new WebMonitorResponse();
        int result = websiteMonitorRepository.deleteCheck(id);
        if (result == 1) {
            webMonitorResponse.setMessage("Check Deleted Successfully!");
        } else {
            webMonitorResponse.setMessage("Something Went Wrong!");
        }
        return webMonitorResponse;
    }

    public WebMonitorResponse updateCheck(WebSiteMonitorInput webSiteMonitorInput) {
        WebMonitorResponse webMonitorResponse = new WebMonitorResponse();
        int result = websiteMonitorRepository.updateCheck(webSiteMonitorInput);
        if (result == 1) {
            webMonitorResponse.setMessage("Check Updated Successfully!");
        } else {
            webMonitorResponse.setMessage("Something Went Wrong!");
        }
        return webMonitorResponse;
    }

    public List<Checks> getAllChecks(int userId) {
        List<Checks> checksList = new ArrayList<>();
        checksList = websiteMonitorRepository.getAllChecks(userId);
        return checksList;
    }

    public WebMonitorResponse startMonitoringJob(int id) {
        WebMonitorResponse webMonitorResponse = new WebMonitorResponse();
        Checks checks = new Checks();
        checks = websiteMonitorRepository.findCheckById(id);
        frequencyData = checks.getFrequency();
        urlData = checks.getUrl();
        userIdData = checks.getUserId();
        jobService.runHelloWorld(checks.getFrequency());
        webMonitorResponse.setMessage("Monitoring Job Started succesfully!");
        return webMonitorResponse;
    }

    public WebMonitorResponse getChecksReponse(int checkId) {
        WebMonitorResponse webMonitorResponse = new WebMonitorResponse();
        List<ChecksResponse> list = websiteMonitorRepository.getAllChecksResponses(checkId);
        ChecksResponse checksResponse = list.get(list.size() - 1);
        webMonitorResponse.setMessage("The Website is " + checksResponse.getStatus() + " since " + checksResponse.getHitTime());
        return webMonitorResponse;
    }
}
