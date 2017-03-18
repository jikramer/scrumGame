drop database scrumgame;

######
#db
######
create database scrumgame;
use scrumgame;


######
#tables
######

CREATE TABLE `scrumgame`.`user` (
  `id` INT NOT NULL,
  `username`  VARCHAR(45) NULL ,
  `password` VARCHAR(45) NULL,
   PRIMARY KEY (`id`),
   UNIQUE(username));


CREATE TABLE `scrumgame`.`user_details` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `user_type` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
   PRIMARY KEY (`id`));



CREATE TABLE `scrumgame`.`user_questionaire_detail` (
  `id` INT NOT NULL,
  `level` INT NOT NULL,
  `score` INT NOT NULL,
  PRIMARY KEY (`id`));

 
######
#procs
######

USE `scrumgame`;

DROP procedure IF EXISTS `spCreateUser`;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spCreateUser`(userName varchar(45), pw varchar(45), firstName varchar(45), lastName varchar(45), userType varchar(45), email varchar(45))
BEGIN
declare maxUserId int;

select max(id) + 1 into maxUserId from user; 

insert into user values
(maxUserId, userName, pw);

insert into user_details values
(maxUserId, firstName, lastName, userType, email);


insert into user_questionaire_detail values
(maxUserId, 1, 0);


END$$
DELIMITER ;

 

USE `scrumgame`;

DROP procedure IF EXISTS `spUpdateScore`;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spUpdateScore`(incomingUserName varchar(45), incomingScore int)
BEGIN
declare userId int;
declare userLevel int;
 
select id into userId from user where username = incomingUserName; 
select level into userLevel from user_questionaire_detail where id = userId;

if (incomingScore >= 80) then
 	
    select userLevel + 1 into  userLevel ;
end if;
 
update user_questionaire_detail
set level = userLevel,
	score = incomingScore
where id = userId;

END$$
DELIMITER ;



# seed tables;

insert into user values
(1, "aquaman", "password");
 
#update scrumgame.user_details set questionaire_level = 1;	




