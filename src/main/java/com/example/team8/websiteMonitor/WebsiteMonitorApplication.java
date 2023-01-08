package com.example.team8.websiteMonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebsiteMonitorApplication {



	public static void main(String[] args) {

		SpringApplication.run(WebsiteMonitorApplication.class, args);
		System.out.println();
		System.out.println();
		System.out.println("****************************************************************************************************************************************************************************************");
		System.out.println("-------------------------------------------------------------------WEBSITE MONITORING TOOL IS RUNNING----------------------------------------------------------------------------");
		System.out.println("****************************************************************************************************************************************************************************************");
	}




}
