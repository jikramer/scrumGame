create database scrumgame
GO
use scrumgame
GO


CREATE TABLE `scrumgame`.`user` (
  `id` INT NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `userdetails_id` INT NULL,
  PRIMARY KEY (`id`));

GO

CREATE TABLE `scrumgame`.`user_details` (
  `id` INT NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `user_type` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


insert into user values
(1, "aquaman", "password",1)
