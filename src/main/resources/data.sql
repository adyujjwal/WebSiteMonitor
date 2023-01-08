create table users(
    userId bigint auto_increment,
    userName varchar(255),
    emailId varchar(255),
    phoneNum varchar(255),
    primary key(userId)
);

create table checks(
    checkId bigint auto_increment,
    userId integer,
    checkName varchar(255),
    frequency varchar(255),
    url varchar(255),
    primary key(checkId)
);

create table checkResponse(
    responseId bigint auto_increment,
    checkId integer,
    status varchar,
    hitTime varchar(255),
    primary key(responseId)
);

insert into USERS(USERNAME,EMAILID,PHONENUM) values('Adway Ujjwal', 'adwayujjwal@gmail.com','+918056643263');

