use cloud;

drop table cloud_name_and_password;

create table cloud_name_and_password
(
    file_path varchar(255),
    user_name varchar(255),
    password varchar(1000) NOT NULL
);

insert into cloud_name_and_password(Id, username, password) VALUES ('1', 'Eve', '{noop}111111');
insert into cloud_name_and_password(Id, username, password) VALUES ('2', 'Wall-E', '{noop}000000');