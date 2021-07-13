
create table author (id serial primary key, firstname varchar(50) not null, secondname varchar(50) not null, middlename varchar(50) not null, dateofborn int default 0, dateofdead int default 0, country varchar(50) not null, description varchar(100) not null)
create table book (id serial primary key, name varchar(50) not null, isbn varchar(50) not null, dateofwriting int default 0, description varchar(100) not null)
create table books_authors (id serial primary key, bid int default -1, auid int default -1, constraint fk1 foreign key (auid) references author (id), constraint fk2 foreign key (bid) references book (id))
