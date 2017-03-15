create database scrumgame
GO
use scrumgame
GO

CREATE TABLE `scrumgame`.`user` (
  `id` INT NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

GO

ALTER TABLE `scrumgame`.`user` 
ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC);

ALTER TABLE `scrumgame`.`user_details` 
ADD COLUMN `questionaire_level`;


CREATE TABLE `scrumgame`.`user_details` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `user_type` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


insert into user values
(1, "aquaman", "password")



USE `scrumgame`;
DROP procedure IF EXISTS `spCreateUser`;

DELIMITER $$
USE `scrumgame`$$
CREATE PROCEDURE spCreateUser(userName varchar(45), pw varchar(45))
BEGIN
declare maxUserId int;

select max(id) + 1 into maxUserId from user; 

insert into user values
(maxUserId, userName, pw);

END;$$

DELIMITER ;

call spCreateUser ("superman", "password");


ALTER TABLE `scrumgame`.`user_details` 
ADD COLUMN `questionaire_level` int;

update scrumgame.user_details set questionaire_level = 1	


insert into scrumgame.user_details values
(1, "aqua", "man", "student", "aq@test.com", 1)

