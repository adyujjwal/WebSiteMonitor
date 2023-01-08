package com.example.team8.websiteMonitor.controller;

import com.example.team8.websiteMonitor.model.Checks;
import com.example.team8.websiteMonitor.model.WebMonitorResponse;
import com.example.team8.websiteMonitor.model.WebMonitorUserInput;
import com.example.team8.websiteMonitor.model.WebSiteMonitorInput;
import com.example.team8.websiteMonitor.service.JobService;
import com.example.team8.websiteMonitor.service.WebSiteMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WebsiteMonitorController {

    @Autowired
    WebSiteMonitorService webSiteMonitorService;

    private final JobService jobService;

    @Autowired
    public WebsiteMonitorController(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<WebMonitorResponse> createNewUser(@RequestBody WebMonitorUserInput webMonitorUserInput) {
        WebMonitorResponse webMonitorResponse = webSiteMonitorService.createNewUser(webMonitorUserInput);
        return new ResponseEntity<WebMonitorResponse>(webMonitorResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/checkWebsiteCurrentStatus", method = RequestMethod.GET)
    public ResponseEntity<WebMonitorResponse> checkCurrentStatus(@RequestParam String url) {
        WebMonitorResponse webMonitorResponse = webSiteMonitorService.getWebsiteCurrentStatus(url);
        return new ResponseEntity<WebMonitorResponse>(webMonitorResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/createCheck", method = RequestMethod.POST)
    public ResponseEntity<WebMonitorResponse> checkStatus(@RequestBody WebSiteMonitorInput webSiteMonitorInput) {
        WebMonitorResponse webMonitorResponse = webSiteMonitorService.createNewCheck(webSiteMonitorInput);
        //jobService.runHelloWorld();
        return new ResponseEntity<WebMonitorResponse>(webMonitorResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteCheck", method = RequestMethod.DELETE)
    public ResponseEntity<WebMonitorResponse> deleteCheck(@RequestParam Integer id) {
        WebMonitorResponse webMonitorResponse = webSiteMonitorService.deleteCheck(id);
        return new ResponseEntity<WebMonitorResponse>(webMonitorResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateCheck", method = RequestMethod.PUT)
    public ResponseEntity<WebMonitorResponse> updateCheck(@RequestBody WebSiteMonitorInput webSiteMonitorInput) {
        WebMonitorResponse webMonitorResponse = webSiteMonitorService.updateCheck(webSiteMonitorInput);
        return new ResponseEntity<WebMonitorResponse>(webMonitorResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllChecks", method = RequestMethod.GET)
    public ResponseEntity<List<Checks>> getAllChecks(@RequestParam int id) {
        List<Checks> list = webSiteMonitorService.getAllChecks(id);
        return new ResponseEntity<List<Checks>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/startMonitoringJob", method = RequestMethod.POST)
    public ResponseEntity<WebMonitorResponse> startMonitoringJob(@RequestParam int id) {
        WebMonitorResponse webMonitorResponse = webSiteMonitorService.startMonitoringJob(id);
        return new ResponseEntity<WebMonitorResponse>(webMonitorResponse, HttpStatus.OK);
    }

    @RequestMapping(value="/getWebsiteStatusResponse", method = RequestMethod.GET)
    public ResponseEntity<WebMonitorResponse> getWebsiteStatusResponse(@RequestParam int id){
        WebMonitorResponse webMonitorResponse = webSiteMonitorService.getChecksReponse(id);
        return new ResponseEntity<WebMonitorResponse>(webMonitorResponse, HttpStatus.OK);
    }
}

