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
  `last_update_dt` datetime NULL);

 CREATE TABLE `scrumgame`.`facultystudent` (
  `facultyId` INT NULL,
  `studentid` INT NULL);


# Answer for the question
create table  scrumgame.QandA(
Question_ID int,
Answer varchar(1) #,
#FOREIGN KEY(Question_ID) REFERENCES scrumgame.MultipleChoiceQuestion(Question_ID)
);


create table scrumgame.MultipleChoiceQuestion(
Question_ID int auto_increment,
Question_1 varchar(1000),
Question_2 varchar(1000),
Question_3 varchar(1000),
Question_4 varchar(1000),

choice_1 varchar(500),
choice_2 varchar(500),
choice_3 varchar(500),
choice_4 varchar(500),
Question_type varchar(30),
CHECK(Question_type in ("Beginner","Intermediate","Expert")),
PRIMARY KEY(Question_ID)
);


 
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
(maxUserId, 1, -999, now());


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
select level into userLevel from user_questionaire_detail where id = userId and last_update_dt = (select max(last_update_dt) from user_questionaire_detail where id =  userId);

if (incomingScore >= 80) then
     select userLevel + 1 into  userLevel ;
end if;

insert user_questionaire_detail values(userId, userLevel, incomingScore, now());
 
#update user_questionaire_detail
#set level = userLevel,
#	score = incomingScore
#where id = userId;

END$$
DELIMITER ;

USE `scrumgame`;
DROP procedure IF EXISTS `spGetFacultyStudent`;

DELIMITER $$
USE `scrumgame`$$
CREATE PROCEDURE `spGetFacultyStudent` (incomingUserName varchar(45))
BEGIN
	
	declare stuId int;
	DROP table IF EXISTS `scrumgame`.`tmp`;

     CREATE TABLE `scrumgame`.`tmp` (
    `id` INT NOT NULL);
 
    
    insert into scrumgame.tmp  
	(select studentId from facultystudent s where s.facultyid =  (select id from user where userName = incomingUserName));
    
     
	SELECT  u.id, u.username, q.level, q.score,  q.last_update_dt
	FROM user u, 
	user_questionaire_detail q,
	facultystudent s 
	where u.id = q.id 
	and u.id =s.studentid
	and s.facultyid = (select id from user where userName = incomingUserName)
 
	and q.last_update_dt in  (  select last_update_dt from user_questionaire_detail u, tmp t 
                               where u.id = t.id 
                               and u.last_update_dt = (select max(last_update_dt) 
                               from user_questionaire_detail where id = t.id))
 	
	order by username desc;
END$$

DELIMITER ;




USE `scrumgame`;
DROP procedure IF EXISTS `spGetFacultyStudentHistory`;

DELIMITER $$
USE `scrumgame`$$
CREATE PROCEDURE `spGetFacultyStudentHistory` (incomingUserName varchar(45))
BEGIN
 
	SELECT  u.username, q.level, q.score, q.last_update_dt 
	FROM user u, 
	user_questionaire_detail q,
	facultystudent s 
	where u.id = q.id 
	and u.id =s.studentid
	and s.facultyid = (select id from user where userName = incomingUserName)
	and q.score > -999 
	order by q.last_update_dt desc;


END$$

DELIMITER ;


USE `scrumgame`;
DROP procedure IF EXISTS `spGetStudentHistory`;

DELIMITER $$
USE `scrumgame`$$
CREATE PROCEDURE `spGetStudentHistory` (incomingUserName varchar(45))
BEGIN
     

	SELECT  u.username, q.level, q.score,  q.last_update_dt 
	FROM user u, 
	user_questionaire_detail q
 	where u.id = q.id 
 	and q.id = (select id from user where userName = incomingUserName)
	and q.score > -999
	order by q.last_update_dt desc;

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



#SP that returns Multiple Choice Question

DROP procedure IF EXISTS `getQuestion`;
 DELIMITER $$
Create procedure getQuestion(
IN levels varchar(30))
begin
(Select Question_1,Question_2,Question_3,Question_4,choice_1,choice_2,choice_3,choice_4 from scrumgame.MultipleChoiceQuestion where Question_type = levels);
 END$$
DELIMITER ;



# seed tables;

insert into user values (1, "aquaman", "password");
insert into facultystudent values(1,0);

 
# Q&A

insert into scrumgame.MultipleChoiceQuestion (Question_1, Question_2, Question_3, Question_4,choice_1,choice_2,choice_3,choice_4,Question_type) values ("q1", "q2", "q3", "Where are the customer requirements stored?","In the Product Backlog","In the Sprint Backlog","In a database","In a Scrum Product Requirement Specification","Beginner");
#insert into scrumgame.MultipleChoiceQuestion (Question,choice_1,choice_2,choice_3,choice_4,Question_type) values ("Which concept is NOT defined in the Scrum Framework?","Scrum Master","Project Manager","Scrum Product Owner","Scrum Product Burndown","Beginner");
Insert into scrumgame.MultipleChoiceQuestion (Question_1, Question_2, Question_3, Question_4,choice_1,choice_2,choice_3,choice_4,Question_type)  values ("q1", "q2", "q3", "What kind of software development projects can be executed by Scrum Project Management Framework?","Complete software packages","Customer projects","Sub-systems, components or parts of bigger systems","All kinds of software development projects","Intermediate");
#Insert into scrumgame.MultipleChoiceQuestion (Question,choice_1,choice_2,choice_3,choice_4,Question_type) values ("What does NOT belong to cornerstones of the agile manifesto?","Individuals and interactions over processes and tools","Working software over comprehensive documentation","Processes over people","Responding to change over following a plan","Intermediate");
Insert into scrumgame.MultipleChoiceQuestion (Question_1, Question_2, Question_3, Question_4,choice_1,choice_2,choice_3,choice_4,Question_type) values ("q1", "q2", "q3", "What are the advantages of the Scrum Framework?","Fine-grained requirements are only defined when they are really needed.","All activities to design, build and test a certain functionality are kept together in one phase.","Changes are expected and welcomed by Scrum team.","All of the given answers","Expert");

 
Insert into scrumgame.QandA values(1,'A');
Insert into scrumgame.QandA values(2,'B');
Insert into scrumgame.QandA values(3,'D');
Insert into scrumgame.QandA values(4,'C');
Insert into scrumgame.QandA values(5,'D');
 

Call getQuestion('Beginner')

