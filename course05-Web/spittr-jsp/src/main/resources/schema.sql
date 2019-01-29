-- identity 对应 java.lang.Long 的自增类型
CREATE TABLE spittle (
  id         IDENTITY PRIMARY KEY,
  message    VARCHAR(140) NOT NULL,
  created_at DATETIME     NOT NULL,
  latitude   DOUBLE,
  longitude  DOUBLE
);

CREATE TABLE spitter (
  id         IDENTITY PRIMARY KEY,
  username   VARCHAR(20) UNIQUE NOT NULL,
  password   VARCHAR(20)        NOT NULL,
  first_name VARCHAR(30)        NOT NULL,
  last_name  VARCHAR(30)        NOT NULL,
  email      VARCHAR(30)        NOT NULL
);

INSERT INTO
  spittle (id, message, created_at)
VALUES
  (1234, 'Hello World!', now()),
  (1235, 'This is my first Spittle!', now());

INSERT INTO
  spitter (id, username, password, first_name, last_name, email)
VALUES
  (233, 'leo', '233', 'just', 'zero', 'alonezero@foxmail.com'),
  (244, 'zero', '233', 'z', 'ero', 'test@qq.com');