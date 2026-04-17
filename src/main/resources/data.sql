INSERT INTO member (name, email, password_hash)
VALUES ('Frank', 'Frank.Castle@gmail.com', '$2a$10$BQshntA.zOjdXoGd5/5z8.zFtKvW53jpD1abUgL6itiVQbkzkkkda');
INSERT INTO member (name, email, password_hash)
VALUES ('Bob', 'bo@gmail.com', '$2a$10$DD6mBCpPjFz16IgekBU91.y8otMHyICyDB2l4yfgwUSKiIfwI3ZDS');
INSERT INTO member (name, email, password_hash)
VALUES ('Steve', 'SteveIsAFurry@gmail.com', '$2a$10$HEYSXamb.H2Vn13U2XZ8o.v70dEIoXWoXHqiGcgjebEXgLefYtTJu');
INSERT INTO member (name, email, password_hash)
VALUES ('Mikkel', 'mikkel@gmail.com', '$2a$10$cQAHYqBHz7JoMR3HYhke0u3uXNJXTrUJSar0Eib7rgdz7z7AKjJcW');


INSERT INTO cat (member_id, name, date_of_birth, date_of_death, sex, ems_code, fertile)
VALUES (4, 'Fluffles', '2024-10-10', null, 'MALE', 'MCO_GR9', false);
INSERT INTO cat (member_id, name, date_of_birth, date_of_death, sex, ems_code, fertile)
VALUES (null, 'The Destroyer of Worlds', '2020-12-11', null, 'FEMALE', 'MCO_GR3', false);
