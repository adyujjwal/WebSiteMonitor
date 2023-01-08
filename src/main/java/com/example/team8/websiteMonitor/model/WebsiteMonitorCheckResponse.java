package com.example.team8.websiteMonitor.model;

import lombok.Data;

@Data
public class WebsiteMonitorCheckResponse {

    private String checkName;

    private String frequency;

    private String url;

}
