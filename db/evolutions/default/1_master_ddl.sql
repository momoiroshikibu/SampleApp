# Users schema
 
# --- !Ups
create table users (
    id bigint(20) not null auto_increment,
    email varchar(255) not null,
    password varchar(255) not null,
    name varchar(255) not null,
    primary key (id)
);


# --- !Downs
 
DROP TABLE users; 