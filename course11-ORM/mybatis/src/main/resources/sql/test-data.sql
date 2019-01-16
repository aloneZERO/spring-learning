INSERT INTO
  spitter (id, username, password, fullName, email, updateByEmail)
VALUES
  (2333, 'leo', '233', 'justzero', 'alonezero@foxmail.com', TRUE),
  (2444, 'zero', '233', 'Zzzzero', 'test@qq.com', FALSE);

INSERT INTO
  spittle (id, spitter, message, postedTime)
VALUES
  (1234, 2333, 'Hello World!', now()),
  (1235, 2333, 'This is my first Spittle!', now());