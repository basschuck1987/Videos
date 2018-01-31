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
select * from user;

insert into user (username,password,name,surname,email,description,Date,role,blocked)values('a' , 'a', 'admin', 'admin', 'email', 'description','2017-05-21', 'ADMIN',0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('u' , 'u', 'user', 'user', 'email1', 'description1', '2017-11-17', 'USER', 0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('s' , 's' 'admin', 'admin', 'email', 'description','2017-05-21', 'ADMIN',0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('s' , 's', 'admin', 'admin', 'email', 'description','2017-05-21', 'ADMIN',0);
insert into user (username,password,name,surname,email,description,Date,role,blocked)values('t' , null, 'admin', 'admin', 'email', 'description','2017-05-21', 'ADMIN',0);

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



insert into UserLikeDislike (idUser,idLikeDislike) values (1, 1);

create table video(

	id bigint auto_increment,
    url varchar(100),
    thumbnail varchar(100),
    description varchar(300),
    visibility ENUM('PUBLIC', 'UNLISTED', 'PRIVATE') NOT NULL DEFAULT 'PUBLIC',
	blocked boolean,
    previews int,
    date datetime,
    owner bigint,
    primary key (id),
    foreign key (owner) references user(id)
    
);

insert into video (url, thumbnail, description, visibility, blocked, previews, date, owner) values ('url', 'thumnail', 'decription', 'UNLISTED', 0, 5,'2017-06-13', 1);
insert into video (url, thumbnail, description, visibility, blocked, previews,date, owner) values ('url1', 'thumnail1', 'decription1', 'PRIVATE', 0, 15,'2017-12-31',2);
insert into video (url, thumbnail, description, visibility, blocked, previews,date, owner) values ('url2', 'thumnail2', 'decription2', 'PUBLIC', 0, 25,'2018-01-27',1);
insert into video (url, thumbnail, description, visibility, blocked, previews,date, owner) values ('url2', 'thumnail2', 'decription3', 'PUBLIC', 0, 3654,'2018-01-27',1);

use videos;
select * from video where visibility in ('PUBLIC','PRIVATE',null);
select * from video where visibility = 'PUBLIC' order by id desc;
select * from video where visibility in ('PUBLIC' , null , null) order by 'id' DESC;
select * from video where visibility in ('PUBLIC' , null , null) order by id DESC;
update video set thumbnail = 'thumbnails/city.jpg' where id = 1;

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

insert into comment (content, date, owner, video) values ('content', '2017-08-09', 1, 1);

insert into comment (content, date, owner, video) values ('content', '2017-08-09', 2, 2);

drop table likedislike;

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
create table UserLikeDislike(
	id bigint auto_increment,
    idUser bigint,
    idLikeDislike bigint,
    primary key (id),
    foreign key (idUser) references user(id),
    foreign key (idLikeDislike) references likeDislike(id)
);


drop database Videos;


