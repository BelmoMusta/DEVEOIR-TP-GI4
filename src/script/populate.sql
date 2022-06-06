INSERT INTO Role(id, name) values (1, 'admin'), (2, 'user');




INSERT INTO USER(ID, USERNAME, PASSWORD) VALUES (1, 'root', '1234');
INSERT INTO USER(ID, USERNAME, PASSWORD) VALUES (2, 'faycal', '0000');
INSERT INTO user_roles(id_user, id_role) VALUES (1,1);
INSERT INTO user_roles(id_user, id_role) VALUES (1,2);
INSERT INTO user_roles(id_user, id_role) VALUES (2,2);


INSERT INTO  materiel(name, type) values ('Chaise A20','chaise');
INSERT INTO  materiel(name, type) values ('Sherlock Holmes','livre');
