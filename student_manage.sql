create table student
(
    id    bigint       not null
        primary key,
    name  varchar(255) not null,
    tel   varchar(255) not null,
    email varchar(255) not null
);

create table user
(
    id       bigint auto_increment
        primary key,
    username varchar(255) not null,
    password varchar(255) not null
);

