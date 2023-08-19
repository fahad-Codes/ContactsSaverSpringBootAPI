drop database logindb;
drop user userlogindb;
create user userlogindb with password 'password';
create database logindb with template=template0 owner=userlogindb;
\connect logindb;
alter default privileges grant all on tables to userlogindb;
alter default privileges grant all on sequences to userlogindb;

create table et_users(
user_id integer primary key not null,
name varchar(20) not null,
Address varchar(20) not null,
City varchar(20) not null,
State varchar(20) not null,
post_code varchar(20) not null,
phone varchar(20) not null,
cell varchar(20) not null,
email varchar(30) not null,
password text not null
);

create sequence et_users_seq increment 1 start 1;

CREATE TABLE contacts(
    contact_id integer primary key not null,
    user_id integer not null,
    name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    postcode VARCHAR(10),
    phone VARCHAR(15),
    cell VARCHAR(15),
    email VARCHAR(255)
);
alter table contacts add constraint con_users_fk
foreign key (user_id) references et_users(user_id);
create sequence et_contact_seq increment 1 start 1;