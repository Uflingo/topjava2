DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals(description,datetime, calories, userid) VALUES
  ('admin lunch',timestamp '2018-01-03 01:00', 1500, 100001),
  ('My dinner', timestamp '2018-01-01 12:00', 1000, 100000),
  ('My lunch', timestamp '2018-01-03 01:00', 1001, 100000)

