CREATE TABLE `HandInHand`.`topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `topic` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45),
  `nickname` VARCHAR(45),
  `password` VARCHAR(45),
  `score1` INT,
  `portrait` VARCHAR(45),
  `score2` INT,
  `remark` VARCHAR(45),
  `male` INT,
  `signature` VARCHAR(100),
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(500),
  `score1` INT,
  `score2` INT,
  `uid` INT NOT NULL,
  `createdTime` INT,
  `picture` VARCHAR(45),
  `title` VARCHAR(45),
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`question_topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tid` INT NOT NULL,
  `qid` INT NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `HandInHand`.`topic` (`topic`) VALUES ('Work');
INSERT INTO `HandInHand`.`topic` (`topic`) VALUES ('HearthStone');
INSERT INTO `HandInHand`.`topic` (`topic`) VALUES ('Emotion');


CREATE TABLE `HandInHand`.`answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(200),
  `score1` INT ,
  `score2` INT ,
  `uid` INT ,
  `qid` INT ,
  `createdTime` VARCHAR(45) ,
  `picture` VARCHAR(45) ,
  `parentAid` INT,
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`user_topic` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `tid` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`favorite` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `qid` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NULL,
  `createdTime` INT NULL,
  `parentCid` INT NULL,
  `content` VARCHAR(200) NULL,
  `aid` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`favorite_question` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `qid` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`favorite_answer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `aid` INT NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `HandInHand`.`topic` (`id`, `topic`) VALUES ('1', '学海无涯');
INSERT INTO `HandInHand`.`topic` (`id`, `topic`) VALUES ('2', '校园生活');
INSERT INTO `HandInHand`.`topic` (`id`, `topic`) VALUES ('3', '情感大话');
INSERT INTO `HandInHand`.`topic` (`id`, `topic`) VALUES ('4', '职业发展');
INSERT INTO `HandInHand`.`topic` (`id`, `topic`) VALUES ('5', '社团活动');
INSERT INTO `HandInHand`.`topic` (`id`, `topic`) VALUES ('6', '吃喝玩乐');
INSERT INTO `HandInHand`.`topic` (`id`, `topic`) VALUES ('7', '今日热门');
INSERT INTO `HandInHand`.`topic` (`id`, `topic`) VALUES ('8', '主编推荐');
INSERT INTO `HandInHand`.`topic` (`id`, `topic`) VALUES ('9', '其他');

CREATE TABLE `HandInHand`.`favorite_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `uid2` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sender` INT NULL,
  `receiver` INT NULL,
  `title` VARCHAR(45) NULL,
  `content` VARCHAR(500) NULL,
  `createdTime` INT NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`user_question_score1` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `qid` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`user_question_score2` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `qid` INT NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `HandInHand`.`user_answer_score1` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `aid` INT NOT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `HandInHand`.`user_answer_score2` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `aid` INT NOT NULL,
  PRIMARY KEY (`id`));



