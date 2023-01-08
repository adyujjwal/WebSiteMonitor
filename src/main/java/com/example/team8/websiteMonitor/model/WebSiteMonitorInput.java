package com.example.team8.websiteMonitor.model;

import lombok.Data;

@Data
public class WebSiteMonitorInput {

    private Integer userId;

    private String checkName;

    private String url;

    private String frequency;
}
