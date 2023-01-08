package com.example.team8.websiteMonitor.model;

import lombok.Data;

@Data
public class Checks {

    private Integer userId;

    private String checkName;

    private String frequency;

    private String url;

}
