CREATE TABLE Spittle (
  id         BIGINT IDENTITY,
  message    VARCHAR(140) NOT NULL,
  created_at DATETIME     NOT NULL,
  latitude   DOUBLE,
  longitude  DOUBLE
);

CREATE TABLE Spitter (
  id         BIGINT IDENTITY,
  username   VARCHAR(20) UNIQUE NOT NULL,
  password   VARCHAR(20)        NOT NULL,
  first_name VARCHAR(30)        NOT NULL,
  last_name  VARCHAR(30)        NOT NULL,
  email      VARCHAR(30)        NOT NULL
);

INSERT INTO Spittle (id, message, created_at) VALUES
  (1234, 'Hello World!', now()),
  (1235, 'This is my first Spittle!', now());

INSERT INTO Spitter (id, username, password, first_name, last_name, email)
VALUES
  (2333333, 'leo', '233', 'just', 'zero', 'alonezero@foxmail.com'),
  (2444444, 'zero', '233', 'z', 'ero', 'test@qq.com');