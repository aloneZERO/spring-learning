CREATE TABLE spitter (
  id            IDENTITY PRIMARY KEY,
  username      VARCHAR(20) UNIQUE NOT NULL,
  password      VARCHAR(20)        NOT NULL,
  fullName      VARCHAR(30)        NOT NULL,
  email         VARCHAR(30)        NOT NULL,
  updateByEmail BOOLEAN DEFAULT FALSE
);

CREATE TABLE spittle (
  id         IDENTITY PRIMARY KEY,
  spitter    BIGINT        NOT NULL,
  message    VARCHAR(2000) NOT NULL,
  postedTime DATETIME      NOT NULL,
  FOREIGN KEY (spitter) REFERENCES spitter (id)
);