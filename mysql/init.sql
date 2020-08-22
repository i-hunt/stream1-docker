create database ops character set = utf8;
create user 'ops'@'%' identified by 'ops';
grant all on ops.* to 'ops'@'%';
