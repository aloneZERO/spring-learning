INSERT INTO
  spitter (id, username, password, fullName, email, updateByEmail)
VALUES
  (2333, 'leo', '233', 'justzero', 'alonezero@foxmail.com', TRUE),
  (2334, 'zero', '233', 'Zzzzero', 'test@qq.com', FALSE),
  (2335, 'r1', '233', 'robot.1', '12306@qq.com', FALSE),
  (2336, 'r2', '233', 'robot.2', 'tecent@qq.com', TRUE );

INSERT INTO spittle (id, spitter, message, postedTime) VALUES (1234, 2333, 'Hello World!', now());
INSERT INTO spittle (id, spitter, message, postedTime) VALUES (1235, 2333, 'This is my first Spittle!', now());
INSERT INTO spittle (id, spitter, message, postedTime) VALUES (1236, 2334, 'Spring Data JPA!', now());
INSERT INTO spittle (id, spitter, message, postedTime) VALUES (1237, 2334, 'oh my god~', now());