
INSERT INTO  materiel_details(id, type, author) values (1, 'LIVRE', 'Jack Lhouwat');
INSERT INTO  materiel_details(id, type, madeof) values (2, 'CHAISE', 'Bois');

INSERT INTO  materiel(name, type, stock) values ( 'Black Beard', 1, 5);
INSERT INTO  materiel(name, type, stock) values ( 'Chaise double' , 2, 2);

INSERT INTO roles (id, name) VALUES (1, 'ADMIN'), (2, 'USER');
INSERT INTO user (id,username,password) VALUES (1, 'admin', '$2a$10$H7CvwUXtEwakSxq.rgvpmu45kzdndPwDTKQdYQJ6MClz/3gfs.OW6');
INSERT INTO user_roles (id_user, id_role) VALUES  (1,2);


