drop database if exists CaseStudyNoSecurity;

create database CaseStudyNoSecurity;

use CaseStudyNoSecurity;

create table User(
	Id int primary key auto_increment,
    firstname varchar(30) not null,
    lastname varchar(30) not null,
    email varchar (45) not null,
    Phone varchar(50) not null,
    username varchar(45) not null UNIQUE,
    `password` varchar(100) not null
);


create table Posts(
	Id int primary key auto_increment,
    body varchar(255) not null,
    created date not null,
    user int not null,
	foreign key (user) references User(Id)
);

create table todo(
	Id int primary key auto_increment,
    name varchar(50) not null,
    description varchar(255),
    completed tinyint not null,
    user int not null,
	foreign key (user) references User(Id)
);

create table income(
	Id int primary key auto_increment,
	name varchar(50) not null,
    description varchar(255),
    amount Decimal(10,2) not null,
    user int not null,
    foreign key (user) references user(Id)	
);

create table expense(
	Id int primary key auto_increment,
	name varchar(50) not null,
    description varchar(255),
    amount Decimal(10,2) not null,
    user int not null,
    foreign key (user) references user(Id)	
);

-- if time allows incoroporate the following
-- create table user_friend(
-- 	Id int primary key auto_increment,
--     user_id int not null,
--     reciever_id int not null,
--  --   Created date not null,
-- --     1=Pending, 2=approved, 3=declined
-- --    status ENUM('1', '2', '3') not null,
--     foreign key (user_id) references User(Id),
--     foreign key (reciever_id) references User(Id)
-- );