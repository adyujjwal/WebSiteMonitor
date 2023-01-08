package com.example.team8.websiteMonitor.model;

import lombok.Data;

@Data
public class ChecksResponse {

    private Integer checkId;

    private String status;

    private String hitTime;

}
