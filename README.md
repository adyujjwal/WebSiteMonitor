![SpringBoot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
# WebSite Up Time Monitoring Service
Website Up Time Monitoring backend service using Spring Boot that can track uptime of any website.

## Contents

- [Introduction](#introduction)
- [APIs](#apis)
- [Running The Application](#sending-pairings-programmatically-via-outlook-with-zoom-integration)

## Introduction

In this backend service we have used Quartz library to schedule jobs that can run and store the up and down status of websites. The Job is user defined and it can be set by user on minutes or hourly basis. For Example If a user wants to check the status of xyz.com every two minutes he can Create a new check for the website and set the time interval for which the job will run and the will hit the website every 2 minutes to check the status and update the data base accordingly.

A single user can have multiple checks on same or different websites. Also a user can viewm,edit,delete and create new Checks.

To watch the working demo of all the APIs: https://youtu.be/UuPCuyVJJvE

## APIs

1. /checkWebsiteCurrentStatus : Check the current status of any website if it's up or down. (Take url of the website as a paramter.)
2. /createNewUser : Creates a new user in the database. After the creation of an user, they can create new checks for any website they want to monitor.( Takes object of user as a parameter with the following properties.)
{
  "name":"",
  "emailId":"",
  "phoneNumber:""
}
3. /createNewCheck : Allows the user to create new checks for the websites they want to monitor, The user has the libery to set the frequeny of checks by minutes or by hours. (Also take an object as a parameter with the following properties.)
{
  "userId":"",
  "checkName":"",
  "frequency":"",
  "url":""
}
4. /updateCheck : Allows user to update the Check name, frequency or url. (Takes the below object as a parameter.)
{
  "checkName":"",
  "frequency":"",
  "url":"
}
5. /deleteCheck : deletes the check from the database. (Take checkId as a parameter.)
6. /getAllCheck : It gets all the check made by the user for websites. (Takes userId as a parameter.)
7. /startMonitoringJob : This API runs the Job on every time interval set by the user, it hits the website to check the status of the website and updates it on to the database. (Takes checkId as a parameter.)


## Running The Application

To run the application, follow the below steps:

1. Open the project in Intellij.
2. Let it install all the dependencies like quartz,H2 etc.
3. When the build is complete run the application.
4. From the console copy the H2 Database code and paste in the H2 console to access the database.

