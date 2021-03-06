USE Videos;
create schema Videos;
drop schema Videos;

create table user(
 	
    id BIGINT AUTO_INCREMENT,
	username varchar (30),
    password varchar (30),
	name varchar (20),
	surname varchar (20),
    email varchar (20),
	description varchar (300),
	Date datetime, 
    role ENUM('USER', 'ADMIN') NOT NULL DEFAULT 'USER',
	blocked boolean,
	primary key (id)
    
);
select  password from user;
select * from user order by name desc;

update user set blocked = true where id = 1;

insert into user (username,password,name,surname,email,description,Date,role,blocked)values('a' , 'a', 'admin', 'admin', 'email', 'description','2017-05-21', 'ADMIN',0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('AAAA' , 'AAAA', 'Sandra', 'Stojanovic', 'email', 'description','2017-05-21', 'USER',0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('sake' , 'sake123', 'Sandra', 'Stojanovic', 'email', 'description','2017-05-21', 'USER',0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('u' , 'u', 'user', 'user', 'email1', 'description1', '2017-11-17', 'USER', 0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('s' , 's' 'admin', 'admin', 'email', 'description','2017-05-21', 'USER',0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('s' , 's', 'admin', 'admin', 'email', 'description','2017-05-21', 'ADMIN',0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('t' , null, 'admin', 'admin', 'email', 'description','2017-05-21', 'ADMIN',0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('sake' , 'sake123', 'Sandra', 'Stojanovic', 'sandramish96gmailcom', 'description','2017-05-21', 'ADMIN',0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('raso' , 'raso123', 'Radovan', 'Maodus', 'email', 'description','2017-05-21', 'USER',0);
delete from user where id in(2,4,1,3,5,6);

delete from user where id = 6;
select * from user;
create table followers(
	id bigint auto_increment,
    idUser bigint,
    idFollower bigint,
    primary key (id), 
    foreign key (idUser) references user(id),
    foreign key (idFollower) references user(id)
);
insert into followers(idUser,idFollower) values( 1, 2);
insert into followers(idUser,idFollower) values(1, 3);
insert into followers(idUser,idFollower) values(1, 4);
drop table followers;
drop table UserLikeDislike;

select * from user order by id DESC;

select u.* from followers f left join user u on f.idUser = u.id where f.idFollower = 3;

select * from user;
select * from followers;



select * from followers where idFollower = 3;

insert into UserLikeDislike (idUser,idLikeDislike) values (1, 1);

update followers set idUser = 4 where id=3;

create table video(

	id bigint auto_increment,
    name varchar(20) not null,
    url varchar(100),
    thumbnail varchar(100),
    description varchar(300),
    visibility ENUM('PUBLIC', 'UNLISTED', 'PRIVATE') NOT NULL DEFAULT 'PUBLIC',
	blocked boolean,
    previews int,
    date datetime,
    owner bigint,
    likeDislikeVisible boolean,
    primary key (id),
    foreign key (owner) references user(id)
    
);
alter table video add column name varchar(20) not null;
select * from video;


alter table video drop column name;
alter table video add column name varchar(20);
alter table video add column likeDislikeVisible boolean;

insert into video (url, thumbnail, description, visibility, blocked, previews, date, owner, name,likeDislikeVisible) values ('url', 'thumnail', 'decription', 'PUBLIC', 0, 5,'2017-06-13', 1, 'aaa', 0);
insert into video (url, thumbnail, description, visibility, blocked, previews, date, owner, name) values ('uT6T-a9Dl28', 'thumnail','UNLISTED' , 'decription', 0, 5,'2017-06-13', 3, 'aaa');
insert into video (url, thumbnail, description, visibility, blocked, previews, date, owner, name) values ('url', 'thumnail', 'decription', 'UNLISTED', 0, 5,'2017-06-13', 1, 'ssss');
insert into video (url, thumbnail, description, visibility, blocked, previews, date, owner, name) values ('url', 'thumnail', 'decription', 'UNLISTED', 0, 5,'2017-06-13', 2, 'ddd');
insert into video (url, thumbnail, description, visibility, blocked, previews, date, owner, name,likeDislikeVisible) values ('url', 'thumnail', 'decription', 'PRIVATE', 0, 5,'2017-06-13', 2, 'aaa', 0);
insert into video (url, thumbnail, description, visibility, blocked, previews, date, owner) values ('url', 'thumnail', 'decription', 'UNLISTED', 0, 5,'2017-06-13', 1);
insert into video (url, thumbnail, description, visibility, blocked, previews,date, owner) values ('url1', 'thumnail1', 'decription1', 'PRIVATE', 0, 15,'2017-12-31',2);
insert into video (url, thumbnail, description, visibility, blocked, previews,date, owner) values ('url2', 'thumnail2', 'decription2', 'PUBLIC', 0, 25,'2018-01-27',1);
insert into video (url, thumbnail, description, visibility, blocked, previews,date, owner) values ('url2', 'thumnail2', 'decription3', 'PUBLIC', 0, 3654,'2018-01-27',1);
insert into video (url, thumbnail, description, visibility, blocked, previews,date, owner) values ('url2', 'thumnail2', 'blabla', 'PUBLIC', 0, 3654,'2018-01-27',1);

select * from video where description like "%l%";
select * from video;
select * from user;
select * from user where id = 1;
select * from video where owner = 1;
use videos;
select * from video where visibility in ('PUBLIC','PRIVATE',null);
select * from video where visibility = 'PUBLIC' order by id desc;
select * from video where visibility in ('PUBLIC' , null , null) order by 'id' DESC;
select * from video where visibility in ('PUBLIC' , null , null) order by id DESC;
update video set thumbnail = 'thumbnails/city.jpg' where id = 1;
update video set thumbnail = 'thumbnails/playButton.png' where id = 2;

create table comment(
	id bigint auto_increment,
    content varchar(300),
    date datetime,
    owner bigint,
    video bigint,
    primary key(id),
    foreign key (owner) references user(id),
    foreign key (video) references video(id)
    
);
select* from followers;
select * from comment order by date;

select * from comment;
insert into comment (content, date, owner, video) values ('content', '2017-05-11', 1, 1);

insert into comment (content, date, owner, video) values ('content', '2017-08-09', 2, 2);
select * from comment where video = 2;
drop table likedislike;
select * from comment;
select * from likeDislike;
 create table likeDislike(
	
    id bigint auto_increment,
    likeOrDislike boolean,
    date datetime,
    video bigint,
    comment bigint,
    primary key (id),
    foreign key (video) references video(id),
    foreign key (comment) references comment(id)
    
);
select*from user;
insert into likeDislike(likeOrDislike, date, video, comment) values (0, '2015-06-11', 1, null);
insert into likeDislike(likeOrDislike, date, video, comment) values (1, '2017-06-11', null, 2);

select* from video;
select* from likeDislike;

create table UserLikeDislike(
	id bigint auto_increment,
    idUser bigint,
    idLikeDislike bigint,
    primary key (id),
    foreign key (idUser) references user(id),
    foreign key (idLikeDislike) references likeDislike(id)
);


drop database Videos;


