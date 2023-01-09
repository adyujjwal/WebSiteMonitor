![SpringBoot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
# WebSiteMonitor

Website Up Time Monitoring backend service using Spring Boot that can track uptime of any website.

Used Quartz library to schedule jobs that can run and store the up and down status of websites. The Job is user defined and it can be set by user on minutes or hourly basis. For Example If a user wants to check the status of xyz.com every two minutes he can Create a new check for the website and set the time interval for which the job will run and the will hit the website every 2 minutes to check the status and update the data base accordingly.

A single user can have multiple checks on same or different websites. Also a user can edit and delete new Checks

To watch the working demo of all the APIs: https://youtu.be/UuPCuyVJJvE
