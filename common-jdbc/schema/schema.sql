CREATE SCHEMA IF NOT EXISTS test DEFAULT CHARACTER SET utf8;
CREATE TABLE IF NOT EXISTS `test`.`t_user` (
   `id` int auto_increment,
   `name` varchar(12) DEFAULT NULL,
PRIMARY KEY(id))
ENGINE=InnoDB DEFAULT CHARSET=utf8;

#master
grant insert,delete,update,select on *.* to 'master'@'%' identified by 'master';
truncate table t_user;
insert into t_user (name) values('master');

#slave
grant select on *.* to 'slave'@'%'  identified by 'slave';
insert into t_user (name) values('slave');
