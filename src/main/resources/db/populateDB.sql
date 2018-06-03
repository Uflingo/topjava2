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

INSERT INTO meals(id, datetime, description, calories, userid) VALUES
  (100002, timestamp '2001-09-28 01:00', 'My dinner', 1000, 100000),
  (100003, timestamp '2001-09-28 02:00', 'My lunch', 1001, 100000)
