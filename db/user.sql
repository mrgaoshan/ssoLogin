create database pm
go
CREATE TABLE user (
	id int PRIMARY KEY AUTO_INCREMENT, 
	username varchar(20) NOT NULL, 
	org_code varchar(20) NULL, 
	co_name varchar(20) NULL, 
	role varchar(50) NULL, 
	role_type varchar(20) NULL, 
	password varchar(50) NOT NULL, 
	email varchar(50) NULL,
	
	gmt_create datetime null,
	gmt_update datetime null
);