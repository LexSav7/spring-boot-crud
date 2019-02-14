drop table if exists books;

create table books(

    id int(11) primary key auto_increment,     
    title varchar(200) not null,     
    description varchar(1500) not null,     
    author varchar(100) not null,     
    isbn varchar(15) not null,     
    year int(4) not null,     
    finished boolean not null

) default charset=utf8;

