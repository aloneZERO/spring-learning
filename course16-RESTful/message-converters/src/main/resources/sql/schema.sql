CREATE TABLE spittle (
  id         IDENTITY PRIMARY KEY,
  message    VARCHAR(140) NOT NULL,
  created_at DATETIME     NOT NULL,
  latitude   DOUBLE,
  longitude  DOUBLE
);