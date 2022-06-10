INSERT INTO Role(id, name) values (1, 'admin'), (2, 'user');




INSERT INTO USER(ID, USERNAME, PASSWORD) VALUES (1, 'root', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4');
INSERT INTO USER(ID, USERNAME, PASSWORD) VALUES (2, 'faycal', '9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0');
INSERT INTO user_roles(id_user, id_role) VALUES (1,1);
INSERT INTO user_roles(id_user, id_role) VALUES (1,2);
INSERT INTO user_roles(id_user, id_role) VALUES (2,2);


INSERT INTO  materiel(name, type) values ('Chaise A20','chaise');
INSERT INTO  materiel(name, type) values ('Sherlock Holmes','livre');
