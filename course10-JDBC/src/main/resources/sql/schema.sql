CREATE TABLE spittle (
  id         BIGINT PRIMARY KEY,
  message    VARCHAR(140) NOT NULL,
  created_at DATETIME     NOT NULL,
  latitude   DOUBLE,
  longitude  DOUBLE
);

CREATE TABLE spitter (
  id         BIGINT PRIMARY KEY,
  username   VARCHAR(20) UNIQUE NOT NULL,
  password   VARCHAR(20)        NOT NULL,
  first_name VARCHAR(30)        NOT NULL,
  last_name  VARCHAR(30)        NOT NULL,
  email      VARCHAR(30)        NOT NULL,
  avatar     VARCHAR(50)
);