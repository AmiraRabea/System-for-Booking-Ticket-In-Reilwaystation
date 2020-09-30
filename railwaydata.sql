create database railwaydata;

create table train_details(


trip_number int  primary key   auto_increment ,
train_number int not null,


from_where varchar (50) not null,


to_where varchar (50) not null,


leave_time varchar(20) ,

 
Reach_time varchar(20) ,


price double not null,


class varchar(20) ,


Coach int not null,


seats int not null,



Allseats int not null



);


create table Reserve (

Reserve_number int auto_increment primary key,

name varchar (30) not null,

from_where varchar (50) ,

 
to_where varchar (50) ,
  
price double not null ,
 
Coach int not null ,

seats int not null ,


class varchar(20),
   
train_number  int ,
 
Date date ,

leave_time varchar(20) ,
 

Ticket_availability varchar(30) not null
 
 

);
