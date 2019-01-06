create table Spittle (
	id BIGINT IDENTITY,
	message varchar(140) not null,
	created_at timestamp not null,
	latitude double,
	longitude double
);

create table Spitter (
	id BIGINT IDENTITY,
	username varchar(20) unique not null,
	password varchar(20) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(30) not null
);

INSERT INTO Spittle(id, message, created_at) VALUES
	(1234, 'Hello World!', current_timestamp()),
	(1235, 'This is my first Spittle!', current_timestamp());

INSERT INTO Spitter(id, username, password, first_name, last_name, email)
VALUES
	(2333333, 'leo', '233', 'just', 'zero', 'alonezero@foxmail.com'),
	(2444444, 'zero', '233', 'z', 'ero', 'test@qq.com');