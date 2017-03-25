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

 CREATE TABLE `scrumgame`.`facultystudent` (
  `facultyId` INT NULL,
  `studentid` INT NULL);
 
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


USE `scrumgame`;
DROP procedure IF EXISTS `spGetFacultyStudent`;

DELIMITER $$
USE `scrumgame`$$
CREATE PROCEDURE `spGetFacultyStudent` (incomingUserName varchar(45))
BEGIN


	SELECT  u.username, q.level, q.score 
	FROM user u, 
	user_questionaire_detail q,
	facultystudent s 
	where u.id = q.id 
	and u.id =s.studentid
	and s.facultyid = (select id from user where userName = incomingUserName);


END$$

DELIMITER ;



USE `scrumgame`;
DROP procedure IF EXISTS `spAssignFacultyStudent`;

DELIMITER $$
USE `scrumgame`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `spAssignFacultyStudent`(incomingFacultyName varchar(45), incomingStudentName varchar(45))
BEGIN
declare facId int;
declare stuId int;
declare stuCount int;
  
select id into stuId from user where username = incomingStudentName;
select id into facId from user where username = incomingFacultyName;

select count(studentId) into stuCount from facultystudent where stuId = studentId;

if (stuCount > 0 ) then
	update facultystudent 
    set facultyId = facId
    where studentid = stuId;
else

	insert into facultystudent values(facId,stuId);

end if;


END$$

DELIMITER ;



# seed tables;

insert into user values
(1, "aquaman", "password");
 
#update scrumgame.user_details set questionaire_level = 1;	

insert into facultystudent values(1,0)



